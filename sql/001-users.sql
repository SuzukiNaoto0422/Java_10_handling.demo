DROP TABLE IF EXISTS users;

CREATE TABLE `users` (
  `id` int UNSIGNED NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `users` (`id`, `name`) VALUES
(1, '鈴木'),
(2, '斎藤'),
(3, '古閑');
