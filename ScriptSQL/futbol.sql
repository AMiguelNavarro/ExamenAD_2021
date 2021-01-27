CREATE DATABASE IF NOT EXISTS liga_futbol;
USE liga_futbol;

CREATE TABLE IF NOT EXISTS equipos
(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL UNIQUE,
	patrocinador VARCHAR(50) 
		DEFAULT 'Sin Patrocinador',
	categoria VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS jugadores
(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	apellidos VARCHAR(50) NOT NULL,
	dorsal TINYINT UNSIGNED,
	id_equipo INT UNSIGNED NOT NULL,
	INDEX (id_equipo),
	FOREIGN KEY (id_equipo)
		REFERENCES equipos (id)
		ON DELETE CASCADE ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS partidos
(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	arbitro VARCHAR(50) DEFAULT 'Sin arbitro',
	campo VARCHAR(50) DEFAULT 'Sin campo',
	incidencias VARCHAR(500)
);
CREATE TABLE IF NOT EXISTS jugador_partido
(
	id_jugador INT UNSIGNED,
	INDEX (id_jugador),
	FOREIGN KEY (id_jugador)
		REFERENCES jugadores (id)
		ON DELETE CASCADE ON UPDATE NO ACTION,
	id_partido INT UNSIGNED,
	INDEX (id_partido),
	FOREIGN KEY (id_partido)
		REFERENCES partidos (id)
		ON DELETE CASCADE ON UPDATE NO ACTION,
	goles TINYINT UNSIGNED DEFAULT 0,
	PRIMARY KEY (id_jugador, id_partido)
);
CREATE TABLE IF NOT EXISTS equipo_partido
(
	id_equipo INT UNSIGNED,
	INDEX (id_equipo),
	FOREIGN KEY (id_equipo)
		REFERENCES equipos (id)
		ON DELETE CASCADE ON UPDATE NO ACTION,
	id_partido INT UNSIGNED,
	INDEX (id_partido),
	FOREIGN KEY (id_partido)
		REFERENCES partidos (id)
		ON DELETE CASCADE ON UPDATE NO ACTION,
	condicion ENUM ('local', 'visitante'),
	goles TINYINT UNSIGNED,
	PRIMARY KEY (id_equipo, id_partido)
);
