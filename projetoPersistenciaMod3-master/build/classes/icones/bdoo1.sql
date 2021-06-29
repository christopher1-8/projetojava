-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 17-Maio-2021 às 17:18
-- Versão do servidor: 5.7.31
-- versão do PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bdoo1`
--
CREATE DATABASE IF NOT EXISTS `bdpoo` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `bdpoo`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbusuario`
--

DROP TABLE IF EXISTS `tbagenda`;
CREATE TABLE IF NOT EXISTS `tbagenda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(80) NOT NULL,
  `telefone` varchar(30) NOT NULL,
  `recado` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbagenda`
--

INSERT INTO `tbagenda` (`id`, `nome`, `email`, `senha`, `telefone`,`recado` ) VALUES
(1, 'Jeferson Leon', 'jeferson@gmail.com', '1', 'adm'),
(2, 'Yasmin da Silveira', 'yasmin@ulbra.br', 'souloucaporalcoolemgel2021', 'usuaria');
COMMIT;
INSERT INTO `bdpoo`.`tbagenda` (`id`, `nome`, `email`, `senha`, `telefone`, `recado`) VALUES 
(NULL, 'yasmin', 'y', '1', '3453645677', 'oi');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
