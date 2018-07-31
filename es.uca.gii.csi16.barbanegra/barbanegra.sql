-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-12-2016 a las 18:46:33
-- Versión del servidor: 5.7.14
-- Versión de PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `barbanegra`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `faccion`
--

CREATE TABLE `faccion` (
  `Id` int(3) NOT NULL,
  `Nombre` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `faccion`
--

INSERT INTO `faccion` (`Id`, `Nombre`) VALUES
(2, 'Jedi'),
(1, 'Sith');

--
-- Disparadores `faccion`
--
DELIMITER $$
CREATE TRIGGER `Faccion_BI` BEFORE INSERT ON `faccion` FOR EACH ROW if NEW.Nombre='' then
	signal sqlstate '45000'
	set message_text = 'El nombre de la facción no puede estar vacía.';
end if
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `Faccion_BU` BEFORE UPDATE ON `faccion` FOR EACH ROW if NEW.Nombre='' then
	signal sqlstate '45000'
    set message_text = 'El nombre de la facción no puede estar vacía.';
end if
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `residente`
--

CREATE TABLE `residente` (
  `Id` int(3) NOT NULL,
  `Codigo` varchar(8) DEFAULT NULL,
  `Nombre` varchar(16) NOT NULL,
  `Apellido` varchar(32) NOT NULL,
  `Edad` int(3) DEFAULT NULL,
  `Id_Faccion` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `residente`
--

INSERT INTO `residente` (`Id`, `Codigo`, `Nombre`, `Apellido`, `Edad`, `Id_Faccion`) VALUES
(1, '452187', 'Juan', 'Vader', 257, 1),
(2, '987129', 'Anakin', 'Skywalker', 80, 1),
(4, '895651', 'Edwart', 'Teach', 52, 1),
(5, '563217', 'Jack', 'Sparrago', 89, 2),
(6, '254789', 'Miguel', 'Ratón', 90, 1),
(7, '394753', 'John', 'Cena', 288, 2),
(8, '123456', 'Fran', 'Perea', 50, 1),
(9, '654321', 'Leonardo', 'DiCaprio', 50, 1),
(10, '135642', 'ParaPepín', 'PonPan', 50, 2),
(11, '118574', 'Manolo', 'Largo', 50, 2),
(13, '221488', 'Juan', 'Rodríguez', 79, 2),
(14, '787542', 'Tokoro', 'Tenosuke', 87, 2),
(15, '389502', 'Natsu', 'Dragneel', 800, 2),
(16, '758923', 'Sergio', 'Garrido', 987, 1),
(17, '438596', 'Fran', 'Guti', 89, 2),
(18, '49560', 'Salvador', 'Aragón', 21, 2),
(19, '484532', 'Kaze', 'Cartagena', 21, 2),
(20, NULL, 'Ash', 'Tomate', NULL, 1),
(21, NULL, 'Crash', 'Bandicoot', NULL, 2),
(25, NULL, 'Pablo', 'Gaviria', 50, 1);

--
-- Disparadores `residente`
--
DELIMITER $$
CREATE TRIGGER `Residente_BI` BEFORE INSERT ON `residente` FOR EACH ROW begin
if NEW.Nombre='' then
	signal sqlstate '45000'
	set message_text = 'El nombre del residente no puede estar vacío.';
end if;
if NEW.Apellido='' then
	signal sqlstate '45000'
	set message_text = 'El apellido del residente no puede estar vacío.';
end if;
if NEW.Edad < 1 then
	signal sqlstate '45000'
	set message_text = 'La edad del residente no puede ser menor que 1.';
end if;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `Residente_BU` BEFORE UPDATE ON `residente` FOR EACH ROW begin
if NEW.Nombre='' then
	signal sqlstate '45000'
	set message_text = 'El nombre del residente no puede estar vacío.';
end if;
if NEW.Apellido='' then
	signal sqlstate '45000'
	set message_text = 'El apellido del residente no puede estar vacío.';
end if;
if NEW.Edad < 1 then
	signal sqlstate '45000'
	set message_text = 'La edad del residente no puede ser menor que 1.';
end if;
end
$$
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `faccion`
--
ALTER TABLE `faccion`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Nombre` (`Nombre`);

--
-- Indices de la tabla `residente`
--
ALTER TABLE `residente`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Codigo` (`Codigo`),
  ADD KEY `Id_Faccion` (`Id_Faccion`),
  ADD KEY `Nombre` (`Nombre`),
  ADD KEY `Apellido` (`Apellido`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `faccion`
--
ALTER TABLE `faccion`
  MODIFY `Id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `residente`
--
ALTER TABLE `residente`
  MODIFY `Id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `residente`
--
ALTER TABLE `residente`
  ADD CONSTRAINT `residente_ibfk_1` FOREIGN KEY (`Id_Faccion`) REFERENCES `faccion` (`Id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
