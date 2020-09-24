-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 24-Set-2020 às 22:03
-- Versão do servidor: 10.4.14-MariaDB
-- versão do PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `desafiobd`
--
CREATE DATABASE IF NOT EXISTS `desafiobd` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `desafiobd`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `log`
--

CREATE TABLE `log` (
  `id_user` varchar(50) DEFAULT NULL,
  `data_criacao` varchar(10) DEFAULT NULL,
  `data_atualizacao` varchar(10) DEFAULT NULL,
  `data_login` varchar(10) DEFAULT NULL,
  `token` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `log`
--

INSERT INTO `log` (`id_user`, `data_criacao`, `data_atualizacao`, `data_login`, `token`) VALUES
('5a79c303-fbc0-4bca-95d1-19c9c7d524e8', '24-09-2020', '24-09-2020', '24-09-2020', 'eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODIiLCJpYXQiOjE2MDA5Nzc2MjksInN1YiI6Imx1Y2FzZGV2c29mdHdhcmVAZ21haWwuY29tIiwiZXhwIjoxNjAwOTc3NzQ5fQ.6TU5-zdvqg1sHfb4UZC5f92f0XX7JqYzQZJ42qF4f3o');

-- --------------------------------------------------------

--
-- Estrutura da tabela `telephone`
--

CREATE TABLE `telephone` (
  `id_user` varchar(50) DEFAULT NULL,
  `ddd` varchar(2) DEFAULT NULL,
  `numero` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `telephone`
--

INSERT INTO `telephone` (`id_user`, `ddd`, `numero`) VALUES
('5a79c303-fbc0-4bca-95d1-19c9c7d524e8', '11', '123456789'),
('5a79c303-fbc0-4bca-95d1-19c9c7d524e8', '11', '123456789'),
('5a79c303-fbc0-4bca-95d1-19c9c7d524e8', '11', '123456789'),
('5a79c303-fbc0-4bca-95d1-19c9c7d524e8', '11', '123456789');

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

CREATE TABLE `user` (
  `id` varchar(50) NOT NULL,
  `nome` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `senha` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`id`, `nome`, `email`, `senha`) VALUES
('5a79c303-fbc0-4bca-95d1-19c9c7d524e8', 'Lucas Sales', 'lucasdevsoftware@gmail.com', '21232F297A57A5A743894A0E4A801FC3');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `log`
--
ALTER TABLE `log`
  ADD KEY `id_user` (`id_user`);

--
-- Índices para tabela `telephone`
--
ALTER TABLE `telephone`
  ADD KEY `id_user` (`id_user`);

--
-- Índices para tabela `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `log`
--
ALTER TABLE `log`
  ADD CONSTRAINT `log_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Limitadores para a tabela `telephone`
--
ALTER TABLE `telephone`
  ADD CONSTRAINT `telephone_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
