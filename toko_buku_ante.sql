-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 21, 2021 at 03:30 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toko_buku_ante`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id_buku` int(11) NOT NULL,
  `judul_buku` varchar(100) NOT NULL,
  `genre` varchar(50) NOT NULL,
  `pengarang` varchar(50) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `harga_buku` int(100) NOT NULL,
  `id_supplier` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id_buku`, `judul_buku`, `genre`, `pengarang`, `penerbit`, `harga_buku`, `id_supplier`) VALUES
(1, 'Si Kucing', 'Dongeng', 'Si andi', 'Erlangga', 15000, 2),
(2, 'Si kancil', 'Dongeng', 'Erlangga', 'Si Mancu', 800000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cust_beli`
--

CREATE TABLE `cust_beli` (
  `id_customer` int(10) NOT NULL,
  `nama_customer` varchar(50) NOT NULL,
  `notelp_customer` varchar(15) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `id_buku` int(11) NOT NULL,
  `jumlah_buku` int(15) NOT NULL,
  `Total_Harga` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cust_beli`
--

INSERT INTO `cust_beli` (`id_customer`, `nama_customer`, `notelp_customer`, `tanggal_transaksi`, `id_buku`, `jumlah_buku`, `Total_Harga`) VALUES
(1, 'Andi', '08125420120', '2021-07-15', 1, 1, 80000),
(2, 'Jessen', '084845124', '2021-07-27', 1, 1, 15000),
(3, 'Mardi', '085413215', '2021-07-13', 2, 1, 800000);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id_supplier` int(11) NOT NULL,
  `nama_supplier` varchar(100) NOT NULL,
  `alamat_supplier` varchar(100) NOT NULL,
  `notelp_supplier` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id_supplier`, `nama_supplier`, `alamat_supplier`, `notelp_supplier`) VALUES
(1, 'Erlangga', 'Jl ahmad dahlan', '089677225'),
(2, 'Dr.supratman', 'Jl Merdeka', '0812554400');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id_buku`),
  ADD KEY `fk_id_supplier` (`id_supplier`);

--
-- Indexes for table `cust_beli`
--
ALTER TABLE `cust_beli`
  ADD PRIMARY KEY (`id_customer`),
  ADD KEY `id_buku` (`id_buku`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buku`
--
ALTER TABLE `buku`
  MODIFY `id_buku` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cust_beli`
--
ALTER TABLE `cust_beli`
  MODIFY `id_customer` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id_supplier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `buku`
--
ALTER TABLE `buku`
  ADD CONSTRAINT `fk_id_supplier` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`);

--
-- Constraints for table `cust_beli`
--
ALTER TABLE `cust_beli`
  ADD CONSTRAINT `cust_beli_ibfk_1` FOREIGN KEY (`id_buku`) REFERENCES `buku` (`id_buku`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
