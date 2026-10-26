CREATE DATABASE cursos_db;
USE cursos_db;

CREATE TABLE carreras (
    id INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY nombre (nombre)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE cursos (
    id INT(11) NOT NULL AUTO_INCREMENT,
    carrera_id INT(11) NOT NULL,
    codigo VARCHAR(20) NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT DEFAULT NULL,
    edificio VARCHAR(50) NOT NULL,
    salon VARCHAR(30) NOT NULL,
    total_horas INT(11) NOT NULL,
    dias_atencion VARCHAR(150) NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    cupo_total INT(11) NOT NULL,
    url_imagen VARCHAR(150) DEFAULT NULL,
    PRIMARY KEY (id),
    KEY fk_cursos_carreras (carrera_id),
    CONSTRAINT fk_cursos_carreras FOREIGN KEY (carrera_id)
    REFERENCES carreras (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE alumnos (
    id INT(11) NOT NULL AUTO_INCREMENT,
    matricula VARCHAR(20) NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    correo_institucional VARCHAR(150) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY matricula (matricula)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE inscripciones (
    id INT(11) NOT NULL AUTO_INCREMENT,
    curso_id INT(11) NOT NULL,
    alumno_id INT(11) NOT NULL,
    fecha_registro DATE NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY curso_alumno (curso_id, alumno_id),
    KEY fk_inscripciones_alumnos (alumno_id),
    CONSTRAINT fk_inscripciones_cursos FOREIGN KEY (curso_id)
    REFERENCES cursos (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_inscripciones_alumnos FOREIGN KEY (alumno_id)
    REFERENCES alumnos (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Insertar registros

INSERT INTO carreras(id,nombre) VALUES
(1,'Ingeniería de Sistemas'),
(2,'Administración'),
(3,'Contaduría');

INSERT INTO cursos(id,carrera_id,codigo,nombre,descripcion,edificio,salon,total_horas,dias_atencion,hora_inicio,hora_fin,cupo_total,url_imagen) VALUES

(1,1,'IS102','Programación Avanzada','Desarrollo de aplicaciones utilizando programación orientada a objetos, estructuras de datos y manejo de excepciones.','C','C-101',80,'Lunes, Miércoles, Viernes','10:00:00','12:00:00',50,'upload/programacion.png'),
(2,1,'IS103','Bases de Datos II','Diseño y administración de bases de datos relacionales mediante consultas SQL.','A','A-102',60,'Martes, Jueves','09:00:00','11:00:00',50,'upload/bases_datos.png'),
(3,1,'IS104','Inteligencia Artificial','Introducción a la inteligencia artificial y sus aplicaciones en la resolución de problemas.','B','B-201',40,'Lunes, Miércoles','12:00:00','14:00:00',40,'upload/inteligencia_artificial.png'),
(4,1,'IS105','Redes de Computadoras','Fundamentos de redes, configuración de dispositivos y comunicación entre computadoras.','A','A-103',60,'Martes, Jueves','11:00:00','13:00:00',50,'upload/redes.png'),
(5,2,'AD101','Administración de Proyectos','Planeación y seguimiento de proyectos mediante la organización de actividades, recursos y tiempos.','D','D-101',40,'Lunes, Viernes','09:00:00','11:00:00',30,'upload/administracion.png'),
(6,2,'AD102','Recursos Humanos','Organización del personal, selección de trabajadores y desarrollo de equipos de trabajo.','D','D-102',40,'Martes, Jueves','12:00:00','14:00:00',30,'upload/recursos_humanos.png'),
(7,3,'CO101','Contabilidad Básica','Registro de operaciones contables y elaboración de estados financieros básicos.','E','E-101',50,'Martes, Jueves','10:00:00','12:00:00',35,'upload/contabilidad.png'),
(8,3,'CO102','Costos y Presupuestos','Identificación de costos y elaboración de presupuestos para la toma de decisiones.','E','E-102',50,'Lunes, Miércoles','09:00:00','11:00:00',35,'upload/costos_presupuestos.png');

INSERT INTO alumnos(id,matricula,nombre,correo_institucional) VALUES
(1,'20250996','Ángel Manuel Saenz Hernández','20250996@uthh.edu.mx'),
(2,'20251068','Itzel Guadalupe Sánchez Bautista','20251068@uthh.edu.mx'),
(3,'20251052','Oscar Cortes Solares','20251052@uthh.edu.mx'),
(4,'20251056','Ángel Angelino González','20251056@uthh.edu.mx');