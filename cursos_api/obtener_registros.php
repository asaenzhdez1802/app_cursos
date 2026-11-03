<?php
header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

$carrera_id = isset($_GET['carrera_id']) ? intval($_GET['carrera_id']) : 0;

try {
    if ($carrera_id > 0) {
        $stmt = $pdo->prepare("SELECT id, carrera_id, nombre, url_imagen, cupo_total FROM cursos WHERE carrera_id = ?");
        $stmt->execute([$carrera_id]);
    } else {
        $stmt = $pdo->query("SELECT id, carrera_id, nombre, url_imagen, cupo_total FROM cursos");
    }

    $cursos = $stmt->fetchAll();
    $total_inscripciones = 0;

    foreach ($cursos as &$curso) {
        $curso['id'] = (int)$curso['id'];
        $curso['carrera_id'] = (int)$curso['carrera_id'];
        $curso['cupo_total'] = (int)$curso['cupo_total'];

        $stmt = $pdo->prepare("SELECT id FROM inscripciones WHERE curso_id = ?");
        $stmt->execute([$curso['id']]);
        $inscripciones = $stmt->fetchAll();
        $inscritos = 0;

        foreach ($inscripciones as $registro) {
            $inscritos = $inscritos + 1;
        }

        $curso['inscritos'] = $inscritos;
        $total_inscripciones = $total_inscripciones + $curso['inscritos'];
    }

    echo json_encode([
        "total_inscripciones" => $total_inscripciones,
        "cursos" => $cursos
    ]);
} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode([
        "error" => "Error al obtener los registros: " . $e->getMessage()
    ]);
}
?>