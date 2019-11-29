# USERS
insert into users (id, username, role) values (1, 'user1', 'DEVICE_OWNER');
insert into users (id, username, role) values (2, 'user2', 'DEVICE_OWNER');
insert into users (id, username, role) values (3, 'user3', 'DEVICE_OWNER');
insert into users (id, username, role) values (4, 'nbabalola2', 'DEVICE_OWNER');
insert into users (id, username, role) values (5, 'iwhisker3', 'DEVICE_OWNER');
insert into users (id, username, role) values (6, 'kmaclennan4', 'DEVICE_OWNER');
insert into users (id, username, role) values (7, 'pshelborne5', 'DEVICE_OWNER');
insert into users (id, username, role) values (8, 'clangeren6', 'DEVICE_OWNER');
insert into users (id, username, role) values (9, 'tsquier7', 'DEVICE_OWNER');
insert into users (id, username, role) values (10, 'sgimert8', 'DEVICE_OWNER');
insert into users (id, username, role) values (11, 'dswiffen9', 'DEVICE_OWNER');
insert into users (id, username, role) values (12, 'admin1', 'ADMIN');

# DEVICE GROUPS
insert into device_group (id, name, user_id) values (1, 'High Contrast', 1);
insert into device_group (id, name, user_id) values (2, 'TV', 3);
insert into device_group (id, name, user_id) values (3, 'Mobile', 1);
insert into device_group (id, name, user_id) values (4, 'Billboards', 3);
insert into device_group (id, name, user_id) values (5, 'Billboards', 1);
insert into device_group (id, name, user_id) values (6, 'Group1', 2);

# ADVERTISING
insert into advertising (id, name, content_path) values (1, 'Candies', '/Users/ilyaguretsky/IdeaProjects/billboard_advertising_V1/billboard_advertising/abstract_storage/advertising/candies');
insert into advertising (id, name, content_path) values (2, 'Cola', '/Users/ilyaguretsky/IdeaProjects/billboard_advertising_V1/billboard_advertising/abstract_storage/advertising/cola');
insert into advertising (id, name, content_path) values (3, 'Courses', '/Users/ilyaguretsky/IdeaProjects/billboard_advertising_V1/billboard_advertising/abstract_storage/advertising/courses');
insert into advertising (id, name, content_path) values (4, 'Federal company', '/Users/ilyaguretsky/IdeaProjects/billboard_advertising_V1/billboard_advertising/abstract_storage/advertising/federal_company');
insert into advertising (id, name, content_path) values (5, 'Marigolds', '/Users/ilyaguretsky/IdeaProjects/billboard_advertising_V1/billboard_advertising/abstract_storage/advertising/marigolds');
insert into advertising (id, name, content_path) values (6, 'Hardware store', '/Users/ilyaguretsky/IdeaProjects/billboard_advertising_V1/billboard_advertising/abstract_storage/advertising/hardware_storage');

# SCHEDULE
insert into schedule (id, name, frequency, user_id) values (1, 'Custom_1', 5, 2);
insert into schedule (id, name, frequency, user_id) values (2, 'Custom_2', 5, 1);
insert into schedule (id, name, frequency, user_id) values (3, 'First_schedule', 7, 2);
insert into schedule (id, name, frequency, user_id) values (4, 'Winter', 6, 3);
insert into schedule (id, name, frequency, user_id) values (5, 'Weekend', 3, 1);
insert into schedule (id, name, frequency, user_id) values (6, 'Holidays', 5, 2);

# DEVICE
insert into device (id, name, device_group_id, user_id, schedule_id) values (1, 'Sony', null, 1, null);
insert into device (id, name, device_group_id, user_id, schedule_id) values (2, 'Xiaomi', null, 2, null);
insert into device (id, name, device_group_id, user_id, schedule_id) values (3, 'Billboard1', null, 1, null);
insert into device (id, name, device_group_id, user_id, schedule_id) values (4, 'Billboard2', null, 2, null);
insert into device (id, name, device_group_id, user_id, schedule_id) values (5, 'Billboard3', null, 3, null);
insert into device (id, name, device_group_id, user_id, schedule_id) values (6, 'Panasonic', null, 3, null);
