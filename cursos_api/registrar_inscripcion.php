<?php
header("Content-type: application/json; charset=UTF-8");
require_once "config/conexion.php";

$jsonlnput= file_get_contents("php://input");
$data = json_decode($jsonlnput, true);

$curso_id=isset($data["curso_id"])?intval($data["curso_id"]):0;
$matricula=isset($data["matricula"])?trim($data["matricula"]):'';
$fecha_registro=isset($data["fecha_registro"])?trim($data["fecha_registro"]):'';    

if ($curso_id<=0 || empty($matricula) || empty($fecha_registro)) {
    echo json_encode([
        "exito"=>false,
        "mensaje"=>"Debe indicar el curso, la matrícula y la fecha de registro"
    ]);
    exit;
}
try {
    $stmt=$pdo->prepare("SELECT id FROM alumnos WHERE matricula=?");
    $stmt->execute([$matricula]);
    $alumno=$stmt->fetch();
    if (!$alumno) {
        echo json_encode([
            "exito"=>false,
            "mensaje"=>"Matricula invalida"
        ]);
        exit;
    }
    $alumno_id=(int)$alumno['id'];


    $stmt=$pdo->prepare("SELECT id, cupo_total FROM cursos WHERE id=?");
    $stmt->execute([$curso_id]);
    $curso=$stmt->fetch();
    if (!$curso) {
        echo json_encode([
        "exito"=>false,
        "mensaje"=>"El curso no existe"
    ]);
    exit;
    }
    $stmt=$pdo->prepare("SELECT id FROM inscripciones WHERE curso_id=? AND alumno_id=?");
    $stmt->execute([$curso_id,$alumno_id]);
    $inscripcion=$stmt->fetch();
    if ($inscripcion) {
        echo json_encode([
            "exito"=>false,
            "mensaje"=>"El alumno ya está inscrito en este curso"
    ]);
    exit;
    }
    $stmt=$pdo->prepare("SELECT id FROM inscripciones WHERE curso_id=?");
    $stmt->execute([$curso_id]);
    $inscripciones=$stmt->fetchAll();
    $inscritos=0;

    foreach ($inscripciones as $registro){
        $inscritos=$inscritos+1;
    }
    if ($inscritos>=(int)$curso['cupo_total']) {
    echo json_encode([
        "exito"=>false,
        "mensaje"=>"el curso ya no tiene cupo disponible"
    ]);
    exit;
    }
    $stmt=$pdo->prepare("INSERT INTO inscripciones(curso_id, alumno_id, fecha_registro) VALUES (?,?,?)");
    $stmt->execute([$curso_id,$alumno_id,$fecha_registro]);

    echo json_encode([
    "exito"=>true,
    "mensaje"=>"Inscripción registrada correctamente"
    ]);

    }catch (Exception $e) {
        header("HTTP/1.1 500 internal Server Error");
        echo json_encode([
            "exito"=>false,
            "mensaje"=>"Error al registrar la inscripción: ".$e->getMessage()
    ]);
    }
?>