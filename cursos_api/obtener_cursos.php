<?php
header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

$carrera_id = isset($_GET['carrera_id']) ? intval($_GET['carrera_id']) : 0;

try {
    if ($carrera_id > 0) {
        $stmt = $pdo->prepare("SELECT id, carrera_id, nombre, salon, url_imagen FROM cursos WHERE carrera_id = ?");
        $stmt->execute([$carrera_id]);
    } else {
        $stmt = $pdo->query("SELECT id, carrera_id, nombre, salon, url_imagen FROM cursos");
    }

    $cursos = $stmt->fetchAll();

    foreach ($cursos as &$curso) {
        $curso['id'] = (int)$curso['id'];
        $curso['carrera_id'] = (int)$curso['carrera_id'];
    }

    echo json_encode($cursos);
} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode([
        "error" => "Error al obtener los cursos: " . $e->getMessage()
    ]);
}
?>