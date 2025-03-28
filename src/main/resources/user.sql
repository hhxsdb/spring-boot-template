-- 插入测试用户数据
INSERT INTO user (username, password, role) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt2s2YO', 'ADMIN'), -- 密码：admin123
('teacher1', '$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9wOmIVA7K1Atu', 'TEACHER'), -- 密码：teacher123
('student1', '$2a$10$glJqPacesU5zIC1RbxZxbOYlLN.LcEwWQxpwLQXrEHxlZzQqhPvSi', 'STUDENT'), -- 密码：student123
('teacher2', '$2a$10$Yd8xNHLDp0.A4M6U7JUQ8u/7laO.QOmLtJ0R1R8qm0gphYY/rKVwi', 'TEACHER'), -- 密码：teacher456
('student2', '$2a$10$YWzYmNLv9dBp7zPmPmEqVeF0YxV3JX54kY.mwHYHo.xNWqD0zTKkm', 'STUDENT'); -- 密码：student456