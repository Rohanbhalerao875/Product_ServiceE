CREATE TABLE productservicnov24batch.category
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    title         VARCHAR(255)          NULL,
    `description` VARCHAR(255)          NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE productservicnov24batch.jt_instructor
(
    id            BIGINT       NOT NULL,
    specilasation VARCHAR(255) NULL,
    CONSTRAINT pk_jt_instructor PRIMARY KEY (id)
);

CREATE TABLE productservicnov24batch.jt_mentor
(
    id         BIGINT       NOT NULL,
    company    VARCHAR(255) NULL,
    avg_rating VARCHAR(255) NULL,
    CONSTRAINT pk_jt_mentor PRIMARY KEY (id)
);

CREATE TABLE productservicnov24batch.jt_student
(
    id     BIGINT       NOT NULL,
    course VARCHAR(255) NULL,
    batch  VARCHAR(255) NULL,
    CONSTRAINT pk_jt_student PRIMARY KEY (id)
);

CREATE TABLE productservicnov24batch.jt_user
(
    id       BIGINT       NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_jt_user PRIMARY KEY (id)
);

CREATE TABLE productservicnov24batch.mps_instructor
(
    id            BIGINT       NOT NULL,
    name          VARCHAR(255) NULL,
    email         VARCHAR(255) NULL,
    password      VARCHAR(255) NULL,
    specilasation VARCHAR(255) NULL,
    CONSTRAINT pk_mps_instructor PRIMARY KEY (id)
);

CREATE TABLE productservicnov24batch.mps_mentor
(
    id         BIGINT       NOT NULL,
    name       VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    company    VARCHAR(255) NULL,
    avg_rating VARCHAR(255) NULL,
    CONSTRAINT pk_mps_mentor PRIMARY KEY (id)
);

CREATE TABLE productservicnov24batch.mps_student
(
    id       BIGINT       NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    course   VARCHAR(255) NULL,
    batch    VARCHAR(255) NULL,
    CONSTRAINT pk_mps_student PRIMARY KEY (id)
);

CREATE TABLE productservicnov24batch.product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    title         VARCHAR(255)          NULL,
    `description` VARCHAR(255)          NULL,
    price         DOUBLE                NULL,
    category_id   BIGINT                NULL,

    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE productservicnov24batch.st_user
(
    id            BIGINT       NOT NULL,
    dtype         VARCHAR(31)  NULL,
    name          VARCHAR(255) NULL,
    email         VARCHAR(255) NULL,
    password      VARCHAR(255) NULL,
    specilasation VARCHAR(255) NULL,
    company       VARCHAR(255) NULL,
    avg_rating    VARCHAR(255) NULL,
    course        VARCHAR(255) NULL,
    batch         VARCHAR(255) NULL,
    CONSTRAINT pk_st_user PRIMARY KEY (id)
);

CREATE TABLE productservicnov24batch.tpc_instructor
(
    id            BIGINT       NOT NULL,
    name          VARCHAR(255) NULL,
    email         VARCHAR(255) NULL,
    password      VARCHAR(255) NULL,
    specilasation VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_instructor PRIMARY KEY (id)
);

CREATE TABLE productservicnov24batch.tpc_mentor
(
    id         BIGINT       NOT NULL,
    name       VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    company    VARCHAR(255) NULL,
    avg_rating VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_mentor PRIMARY KEY (id)
);

CREATE TABLE productservicnov24batch.tpc_student
(
    id       BIGINT       NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    course   VARCHAR(255) NULL,
    batch    VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_student PRIMARY KEY (id)
);

CREATE TABLE productservicnov24batch.tpc_user
(
    id       BIGINT       NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_user PRIMARY KEY (id)
);

ALTER TABLE productservicnov24batch.jt_instructor
    ADD CONSTRAINT FK_JT_INSTRUCTOR_ON_ID FOREIGN KEY (id) REFERENCES productservicnov24batch.jt_user (id);

ALTER TABLE productservicnov24batch.jt_mentor
    ADD CONSTRAINT FK_JT_MENTOR_ON_ID FOREIGN KEY (id) REFERENCES productservicnov24batch.jt_user (id);

ALTER TABLE productservicnov24batch.jt_student
    ADD CONSTRAINT FK_JT_STUDENT_ON_ID FOREIGN KEY (id) REFERENCES productservicnov24batch.jt_user (id);

ALTER TABLE productservicnov24batch.product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES productservicnov24batch.category (id);