-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 23-02-2026 a las 03:01:00
-- Versión del servidor: 9.1.0
-- Versión de PHP: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clinica`
--
CREATE DATABASE IF NOT EXISTS `clinica` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `clinica`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agenda`
--

DROP TABLE IF EXISTS `agenda`;
CREATE TABLE IF NOT EXISTS `agenda` (
  `IDAgenda` int NOT NULL AUTO_INCREMENT,
  `IDMedico` int DEFAULT NULL,
  `FechaHora` datetime DEFAULT NULL,
  PRIMARY KEY (`IDAgenda`),
  KEY `IDMedico` (`IDMedico`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencia`
--

DROP TABLE IF EXISTS `asistencia`;
CREATE TABLE IF NOT EXISTS `asistencia` (
  `IDAsistencia` int NOT NULL AUTO_INCREMENT,
  `IDExpediente` int DEFAULT NULL,
  `IDAgenda` int DEFAULT NULL,
  PRIMARY KEY (`IDAsistencia`),
  KEY `IDExpediente` (`IDExpediente`),
  KEY `IDAgenda` (`IDAgenda`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `atencion`
--

DROP TABLE IF EXISTS `atencion`;
CREATE TABLE IF NOT EXISTS `atencion` (
  `IDAtencion` int NOT NULL AUTO_INCREMENT,
  `IDVisita` int DEFAULT NULL,
  `IDMedico` int DEFAULT NULL,
  `FechaHora` datetime DEFAULT NULL,
  `ConsultaPor` varchar(50) DEFAULT NULL,
  `PresenteEnfermedad` varchar(2000) DEFAULT NULL,
  `Diagnostico` varchar(200) DEFAULT NULL,
  `Tratamiento` varchar(2000) DEFAULT NULL,
  `Anotaciones` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`IDAtencion`),
  KEY `IDVisita` (`IDVisita`),
  KEY `IDMedico` (`IDMedico`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `expediente`
--

DROP TABLE IF EXISTS `expediente`;
CREATE TABLE IF NOT EXISTS `expediente` (
  `IDExpediente` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) DEFAULT NULL,
  `Sexo` char(1) DEFAULT NULL,
  `FechaNacimiento` date DEFAULT NULL,
  `TipoDocumento` varchar(20) DEFAULT NULL,
  `NumeroDocumento` varchar(20) DEFAULT NULL,
  `NombreGuardian` varchar(50) DEFAULT NULL,
  `TipoDocumentoGuardian` varchar(20) DEFAULT NULL,
  `NumeroDocumentoGuardian` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IDExpediente`)
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medico`
--

DROP TABLE IF EXISTS `medico`;
CREATE TABLE IF NOT EXISTS `medico` (
  `IDMedico` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IDMedico`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visita`
--

DROP TABLE IF EXISTS `visita`;
CREATE TABLE IF NOT EXISTS `visita` (
  `IDVisita` int NOT NULL AUTO_INCREMENT,
  `IDExpediente` int DEFAULT NULL,
  `IDMedico` int DEFAULT NULL,
  `FechaHora` datetime DEFAULT NULL,
  `Circumstancia` varchar(200) DEFAULT NULL,
  `Triage` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IDVisita`),
  KEY `IDExpediente` (`IDExpediente`),
  KEY `IDMedico` (`IDMedico`)
) ;
--
-- Base de datos: `monolitico_db`
--
CREATE DATABASE IF NOT EXISTS `monolitico_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `monolitico_db`;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
