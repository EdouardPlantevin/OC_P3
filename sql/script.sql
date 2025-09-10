
INSERT INTO `oc_user` (`id`, `created_at`, `email`, `name`, `password`, `updated_at`) VALUES
    (1, '2025-09-09 09:22:29.446000', 'test@test.com', 'Edouard', '$2a$10$iuzY1nqPW1J91YecTSIVhelMEKwBpPWILS3Qq2XFa9l9z.FVxS7Ye', '2025-09-09 09:22:29.446000');



INSERT INTO `rental` (`id`, `created_at`, `description`, `name`, `picture`, `price`, `surface`, `updated_at`, `owner_id`) VALUES
                                                                                                                              (2, '2025-09-10 13:47:37.479000', 'Une maison à la montagne pouvant recevoir 15 personnes ', 'Gîte de montagne', 'c0be369e-d58c-47b0-8d41-56aafd9952b8_Une maison à la montagne, réaliste style pour une location, image très réaliste.jpeg', 450, 300, '2025-09-10 13:47:37.479000', 1),
                                                                                                                              (3, '2025-09-10 13:48:31.791000', 'Profitez d\'une belle maison au pied de l\'eau. Un séjour agréable vous attends', 'Maison bord de mer', '0fba4904-0e97-416a-8924-588cc2a6e4a8_Une maison en bord de mer.jpeg', 600, 135, '2025-09-10 13:48:31.791000', 1),
                                                                                                                              (4, '2025-09-10 13:52:01.313000', 'Venez découvrir la légende du monstre du loch ness, à petit prix', 'Maison au bord du lac', '7ab9ba71-db85-4cfb-8007-3a109624e693_Une maison au bord d\'un lac, réaliste style pour une location, image très réaliste.jpeg', 1300, 600, '2025-09-10 13:52:01.313000', 1);

