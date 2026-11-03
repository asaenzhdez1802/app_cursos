<?php

header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

$matricula=isset($_GET['matricula'])?$_GET['matricula']:'';

if (empty($matricula)) {
    header("HTTP/1.1 400 Bad Request");
    echo json_encode([
        "exito"=>false,
        "mensaje"=>"Debe indicar la matrícula del alumno"
    ]);
    exit;   
}
try{
    $stmt= $pdo->prepare("SELECT id, matricula, nombre,correo_institucional FROM alumnos WHERE matricula=?");
    $stmt->execute([$matricula]);
    $alumno=$stmt->fetch();

    if ($alumno){
        echo json_encode([
            "exito"=>true,
            "mensaje"=>"alumno encontrado",
            "id"=>(int)$alumno["id"],
            "matricula"=>$alumno['matricula'],
            "nombre"=>$alumno['nombre'],
            "correo_institucional"=>$alumno['correo_institucional']
        ]);
    }else{
        echo json_encode([
            "exito"=>false,
            "mensaje"=>"matricula invalida"
        ]);
    }

}catch(Exception $e){
    header("HTTP/1.1 500 internal Server Error");
    echo json_encode([
        "exito"=>false,
        "mensaje"=>"Error al obtener alumno".$e->getMessage()
        ]);
}
?>