-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 16, 2021 at 07:12 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `psss_kks`
--

-- --------------------------------------------------------

--
-- Table structure for table `clubs`
--

CREATE TABLE `clubs` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `town` varchar(255) NOT NULL,
  `wins` int(11) NOT NULL,
  `losses` int(11) NOT NULL,
  `logo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clubs`
--

INSERT INTO `clubs` (`id`, `name`, `town`, `wins`, `losses`, `logo`) VALUES
(1, 'Crvena Zvezda', 'Beograd', 0, 1, 'https://upload.wikimedia.org/wikipedia/en/thumb/0/01/KK_Crvena_zvezda_logo.svg/150px-KK_Crvena_zvezda_logo.svg.png'),
(2, 'Partizan', 'Beograd', 0, 0, 'https://upload.wikimedia.org/wikipedia/en/thumb/8/80/KK_Partizan_logo.svg/800px-KK_Partizan_logo.svg.png'),
(3, 'FMP', 'Beograd', 1, 0, 'https://upload.wikimedia.org/wikipedia/en/d/d7/KK_FMP_Beograd.png'),
(4, 'Borac', 'Cacak', 0, 0, 'https://upload.wikimedia.org/wikipedia/en/b/b3/KKBoracCacak.png'),
(5, 'Sloga', 'Kraljevo', 2, 0, 'https://upload.wikimedia.org/wikipedia/en/3/3d/Kksloga.png'),
(6, 'Napredak', 'Aleksinac', 0, 1, 'https://upload.wikimedia.org/wikipedia/en/3/3b/Kknapredak-aleksinac.png'),
(7, 'Radnicki', 'Kragujevac', 0, 0, 'https://upload.wikimedia.org/wikipedia/en/b/b7/KKK-Radnicki-logo.png'),
(8, 'Sloboda', 'Uzice', 0, 0, 'https://upload.wikimedia.org/wikipedia/en/d/d1/Sloboda-uzice-kk.png'),
(9, 'Zlatibor', 'Cajetina', 0, 0, 'https://upload.wikimedia.org/wikipedia/en/d/db/Zlatibor_%C4%8Cajetina_logo.png'),
(10, 'Mladost', 'Zemun', 0, 1, 'https://upload.wikimedia.org/wikipedia/en/8/8f/KK-maldost-zemun-logo.png');

-- --------------------------------------------------------

--
-- Table structure for table `games`
--

CREATE TABLE `games` (
  `id` int(11) NOT NULL,
  `homeId` int(11) NOT NULL,
  `awayId` int(11) NOT NULL,
  `winner` int(11) NOT NULL,
  `date` date NOT NULL,
  `finished` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `games`
--

INSERT INTO `games` (`id`, `homeId`, `awayId`, `winner`, `date`, `finished`) VALUES
(1, 1, 3, 2, '2021-09-16', 1),
(2, 2, 5, 0, '2021-09-17', 0),
(3, 1, 2, 0, '2021-09-17', 0),
(4, 5, 6, 1, '2021-09-15', 1),
(6, 9, 10, 0, '2021-09-25', 0),
(7, 7, 4, 0, '2021-09-22', 0),
(8, 10, 5, 2, '2021-09-16', 1),
(9, 7, 1, 0, '2021-09-18', 0),
(10, 6, 3, 0, '2021-09-18', 0);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `role`) VALUES
(1, 'uprava'),
(2, 'navijac');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL,
  `roleId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `isAdmin`, `roleId`) VALUES
(1, 'admin', 'admin@gmail.com', 'D033E22AE348AEB5660FC2140AEC35850C4DA997', 1, 1),
(2, 'djordje', 'djordje@gmail.com', 'AEE2CEBFDDE6F236F3ACB680AB8AC0E1A644848C', 0, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clubs`
--
ALTER TABLE `clubs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clubs`
--
ALTER TABLE `clubs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `games`
--
ALTER TABLE `games`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
