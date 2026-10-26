<?php
header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

try {
    $stmt = $pdo->query("SELECT id, nombre FROM carreras ORDER BY nombre");
    $carreras = $stmt->fetchAll();

    foreach ($carreras as &$carrera) {
        $carrera['id'] = (int)$carrera['id'];
    }

    echo json_encode($carreras);
} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode([
        "error" => "Error al obtener las carreras: " . $e->getMessage()
    ]);
}
?>