-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 23. Apr 2021 um 07:16
-- Server-Version: 10.4.18-MariaDB
-- PHP-Version: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `group1_database`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `employee`
--

CREATE TABLE `employee` (
  `EmployeeID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Surname` varchar(255) NOT NULL,
  `Age` int(11) NOT NULL,
  `StationID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `employee`
--

INSERT INTO `employee` (`EmployeeID`, `Name`, `Surname`, `Age`, `StationID`) VALUES
(1, 'Torsten', 'Schmidt', 30, 1),
(2, 'Ursel', 'Fischer', 34, 1),
(3, 'Brigitte', 'Altmann', 28, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `incidents`
--

CREATE TABLE `incidents` (
  `IncidentID` int(11) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `ResID` int(11) NOT NULL,
  `ShiftID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `medication`
--

CREATE TABLE `medication` (
  `MedID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `medication`
--

INSERT INTO `medication` (`MedID`, `Name`) VALUES
(1, 'Sotalol'),
(2, 'Trimipramin'),
(3, 'Trimipramin');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `medplan`
--

CREATE TABLE `medplan` (
  `MedID` int(11) NOT NULL,
  `ResID` int(11) NOT NULL,
  `Intake_Frequency` double NOT NULL,
  `Concentration` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `senior_resident`
--

CREATE TABLE `senior_resident` (
  `ID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Surname` varchar(255) NOT NULL,
  `Room` int(11) NOT NULL,
  `StationID` int(11) NOT NULL,
  `Age` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `senior_resident`
--

INSERT INTO `senior_resident` (`ID`, `Name`, `Surname`, `Room`, `StationID`, `Age`) VALUES
(1, 'Robert', 'Ibe', 10, 1, 85),
(2, 'Marleen', 'Hastung', 11, 1, 79),
(3, 'Inge', 'Steinecke', 12, 1, 87);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `shift_schedule`
--

CREATE TABLE `shift_schedule` (
  `ShiftID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Category` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `station`
--

CREATE TABLE `station` (
  `StationID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `station`
--

INSERT INTO `station` (`StationID`, `Name`) VALUES
(1, 'Herbstfrische'),
(2, 'Sommerbrise'),
(3, 'Winterduft');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`EmployeeID`),
  ADD UNIQUE KEY `EmployeeID` (`EmployeeID`);

--
-- Indizes für die Tabelle `incidents`
--
ALTER TABLE `incidents`
  ADD PRIMARY KEY (`IncidentID`),
  ADD UNIQUE KEY `IncidentID` (`IncidentID`);

--
-- Indizes für die Tabelle `medication`
--
ALTER TABLE `medication`
  ADD PRIMARY KEY (`MedID`),
  ADD UNIQUE KEY `MedID` (`MedID`);

--
-- Indizes für die Tabelle `medplan`
--
ALTER TABLE `medplan`
  ADD PRIMARY KEY (`MedID`),
  ADD UNIQUE KEY `MedID` (`MedID`);

--
-- Indizes für die Tabelle `senior_resident`
--
ALTER TABLE `senior_resident`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`);

--
-- Indizes für die Tabelle `shift_schedule`
--
ALTER TABLE `shift_schedule`
  ADD PRIMARY KEY (`ShiftID`),
  ADD UNIQUE KEY `ShiftID` (`ShiftID`);

--
-- Indizes für die Tabelle `station`
--
ALTER TABLE `station`
  ADD PRIMARY KEY (`StationID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `employee`
--
ALTER TABLE `employee`
  MODIFY `EmployeeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `incidents`
--
ALTER TABLE `incidents`
  MODIFY `IncidentID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `medication`
--
ALTER TABLE `medication`
  MODIFY `MedID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `medplan`
--
ALTER TABLE `medplan`
  MODIFY `MedID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `senior_resident`
--
ALTER TABLE `senior_resident`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `shift_schedule`
--
ALTER TABLE `shift_schedule`
  MODIFY `ShiftID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `station`
--
ALTER TABLE `station`
  MODIFY `StationID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
