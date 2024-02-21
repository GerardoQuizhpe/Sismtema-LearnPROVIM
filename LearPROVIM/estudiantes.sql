-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-02-2024 a las 16:13:09
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `estudiantes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recomendacion`
--

CREATE TABLE `recomendacion` (
  `nombre` varchar(300) NOT NULL,
  `url` varchar(300) NOT NULL,
  `nivel` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `recomendacion`
--

INSERT INTO `recomendacion` (`nombre`, `url`, `nivel`) VALUES
('Aprender a programar: los conceptos básicos', 'https://www.ionos.es/digitalguide/paginas-web/desarrollo-web/aprender-a-programar-introduccion-y-conceptos-basicos/', 'Basico'),
('Ejemplos de Programación Orientada a Objetos', 'https://ejemplo.com.ar/programacion-orientada-a-objetos-ejemplos/\\', 'Medio'),
('Capítulo 4. Programación avanzada', 'https://uniwebsidad.com/libros/javascript/capitulo-4\\', 'Avanzado'),
('Programación Movil', 'https://www.youtube.com/watch?v=-pWSQYpkkjk', 'Avanzado'),
('Programación Orientada a Objetos explicada', 'https://www.youtube.com/watch?v=Nka4JSBgf7I', 'Medio'),
('Curso de programación desde cero | Principio básico de programación #1', 'https://www.youtube.com/watch?v=AEiRa5xZaZw', 'Basico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `nombre` varchar(50) NOT NULL,
  `ciclo` int(80) NOT NULL,
  `materia` varchar(60) NOT NULL,
  `nivel` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`nombre`, `ciclo`, `materia`, `nivel`) VALUES
('Maria Hurtado', 1, 'Teoría de la programación', 'Basico'),
('Marco Sanchez', 2, 'Programación orientada a objetos', 'Medio'),
('Julio Pesantez', 5, 'Desarrollo basado en plataformas', 'Avanzado'),
('Paola Quezada', 5, 'Desarrollo basado en plataformas', 'Avanzado'),
('Maritza Chamba', 2, 'Programación orientada a objetos', 'Medio'),
('Andres Ochoa', 1, 'Teoría de la programación', 'Basico');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
