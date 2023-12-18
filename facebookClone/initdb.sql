
CREATE DATABASE IF NOT EXISTS FP;
USE FP;

CREATE TABLE final_project_user (
                                    user_id VARCHAR(255) NOT NULL,
                                    user_name VARCHAR(255) UNIQUE,
                                    email VARCHAR(255) UNIQUE,
                                    create_time DATETIME,
                                    password VARCHAR(255),
                                    PRIMARY KEY (user_id)
);

CREATE TABLE final_project_post (
                                    post_id VARCHAR(255) NOT NULL,
                                    post_content TEXT,
                                    post_time DATETIME,
                                    user_id VARCHAR(255),
                                    PRIMARY KEY (post_id),
                                    FOREIGN KEY (user_id) REFERENCES final_project_user(user_id)
);

CREATE TABLE final_project_comment (
                                       comment_id VARCHAR(255) NOT NULL,
                                       comment_content VARCHAR(1000),
                                       comment_time DATETIME,
                                       user_id VARCHAR(255),
                                       post_id VARCHAR(255),
                                       PRIMARY KEY (comment_id),
                                       FOREIGN KEY (user_id) REFERENCES final_project_user(user_id),
                                       FOREIGN KEY (post_id) REFERENCES final_project_post(post_id)
);

