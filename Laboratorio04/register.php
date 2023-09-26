<?php
declare(strict_types=1);
use Firebase\JWT\JWT;
require_once('../vendor/autoload.php');
require_once('../config/connection.php');

// Recepción de los valores en el cuerpo de la llamada POST
$json = file_get_contents('php://input'); // Lee el JSON del cuerpo de la solicitud
$data = json_decode($json, true); // Decodifica el JSON en un array asociativo

$usuario = $data['username'];
$name = $data['name'];
$contrasena = $data['password'];

// Verifico si el usuario ya existe en la base de datos
$sql = "SELECT id, username FROM user WHERE username = '$usuario'";
$resultado = $conexion->query($sql);

if ($resultado->num_rows > 0) {
    echo "error_user_exists";
} else {
    // Insertar nuevo usuario en la base de datos
    $hashedPassword = md5($contrasena); // Hash de la contraseña (esto no es seguro, se recomienda usar una técnica más segura)
    $sqlInsert = "INSERT INTO user (username, nombre, password) VALUES ('$usuario', '$name', '$hashedPassword')";
    
    if ($conexion->query($sqlInsert) === TRUE) {
        // Crear token
        $secret_Key  = '68V0zWFrS72GbpPreidkQFLfj4v9m3Ti+DXc8OB0gcM=';
        $date = new DateTimeImmutable();
        $expire_at = $date->modify('+6 minutes')->getTimestamp();
        $domainName = "edu.uvg.loginapp";
        $username = $usuario;
        
        $request_data = [
            'iat'  => $date->getTimestamp(),
            'iss'  => $domainName,
            'nbf'  => $date->getTimestamp(),
            'exp'  => $expire_at,
            'userName' => $username,
        ];

        $token = JWT::encode(
            $request_data,
            $secret_Key,
            'HS512'
        );

        // Guardar el token en la base de datos para el autoinicio de sesión
        $user_id = $conexion->insert_id;
        $expire_at_formatted = date('Y-m-d', $expire_at);
        $sqlUpdateToken = "UPDATE user SET token = '$token', token_expiracy = '$expire_at_formatted' WHERE id = $user_id";
        
        if ($conexion->query($sqlUpdateToken) === TRUE) {
            echo $token;
        } else {
            echo "Error al actualizar el token: " . $conexion->error;
        }
    } else {
        echo "Error al registrar el usuario: " . $conexion->error;
    }
}

// Cierra la conexión a la base de datos
$conexion->close();
