use ps_tema2;

INSERT INTO zboruri (plecare, destinatie, nrLocuri) VALUES
('Bucharest', 'London', 200),
('Cluj', 'Paris', 150),
('Timisoara', 'Berlin', 180),
('Iasi', 'Rome', 160),
('Constanta', 'Madrid', 175),
('Sibiu', 'Vienna', 190),
('Oradea', 'Budapest', 140),
('Brasov', 'Amsterdam', 155),
('Craiova', 'Prague', 165),
('Galati', 'Warsaw', 170),
('Suceava', 'Brussels', 180),
('Bucharest', 'Milan', 185),
('Sibiu', 'Munich', 150),
('Arad', 'Barcelona', 160),
('Ploiesti', 'Zurich', 145);

INSERT INTO utilizatori (username, password, tip) VALUES
('admin1', 'pass1', 1),
('admin2', 'pass2', 1),
('admin3', 'pass3', 1),
('user1', 'pass4', 2),
('user2', 'pass5', 2),
('user3', 'pass6', 2),
('user4', 'pass7', 2),
('user5', 'pass8', 2),
('user6', 'pass9', 2),
('user7', 'pass10', 2),
('user8', 'pass11', 2),
('user9', 'pass12', 2),
('user10', 'pass13', 2),
('user11', 'pass14', 2),
('user12', 'pass15', 2);

-- Insert 15 values into passengers (pasageri)
INSERT INTO passengers (nume) VALUES
('John'),
('Jane'),
('Michael'),
('Emily'),
('David'),
('Sophia'),
('Daniel'),
('Olivia'),
('James'),
('Isabella'),
('Benjamin'),
('Mia'),
('Liam'),
('Charlotte'),
('Elijah');
