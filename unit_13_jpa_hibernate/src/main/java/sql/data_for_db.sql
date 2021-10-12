INSERT INTO unit_13_db.lessons (id, ends_at, name, starts_at,teacher_id,topic_id) VALUES (1, '2021-11-10 15:00:00', 'name1','2021-11-01 12:00:00', 1, 2);
INSERT INTO unit_13_db.lessons (id, ends_at, name, starts_at,teacher_id,topic_id) VALUES (2, '2021-11-10 13:00:00', 'name2','2021-11-01 10:00:00', 2, 1);
INSERT INTO unit_13_db.lessons (id, ends_at, name, starts_at,teacher_id,topic_id) VALUES (3, '2021-11-10 16:00:00', 'name3','2021-11-01 13:00:00', 3, 3);

INSERT INTO unit_13_db.students (id, name, group_id) VALUES (1, 'Ivan Ivanov', 1);
INSERT INTO unit_13_db.students (id, name, group_id) VALUES (2, 'Petr Petrov', 2);
INSERT INTO unit_13_db.students (id, name, group_id) VALUES (3, 'Victor Davis', 3);

INSERT INTO unit_13_db.teachers (id, name) VALUES (1, 'Mike Williams');
INSERT INTO unit_13_db.teachers (id, name) VALUES (2, 'Rosy Peters');
INSERT INTO unit_13_db.teachers (id, name) VALUES (3, 'John Mills');

INSERT INTO unit_13_db.courses_topics (course_id, topic_id) VALUES (1, 1);
INSERT INTO unit_13_db.courses_topics (course_id, topic_id) VALUES (2, 3);
INSERT INTO unit_13_db.courses_topics (course_id, topic_id) VALUES (3, 2);

INSERT INTO unit_13_db.course_groups (id, name, course_id) VALUES (1, 'a-level',1);
INSERT INTO unit_13_db.course_groups (id, name, course_id) VALUES (2, 'nix',2);
INSERT INTO unit_13_db.course_groups (id, name, course_id) VALUES (3, 'epam',3);

INSERT INTO unit_13_db.courses (id, name) VALUES (1, 'course_1');
INSERT INTO unit_13_db.courses (id, name) VALUES (2, 'course_2');
INSERT INTO unit_13_db.courses (id, name) VALUES (3, 'course_3');

INSERT INTO unit_13_db.topics (id, name) VALUES (1, 'topic_1');
INSERT INTO unit_13_db.topics (id, name) VALUES (2, 'topic_2');
INSERT INTO unit_13_db.topics (id, name) VALUES (3, 'topic_3');