DROP TABLE IF EXISTS freelancer_profiles CASCADE;

DROP TABLE IF EXISTS client_profiles CASCADE;

CREATE TABLE freelancer_profiles (

    id SERIAL PRIMARY KEY,

    user_id INT UNIQUE,

    profession VARCHAR(150),

    bio TEXT,

    experience INT,

    phone VARCHAR(20),

    address TEXT,

    linkedin_url TEXT,

    github_url TEXT,

    other_profile_url TEXT,

    resume_url TEXT,

    portfolio_url TEXT,

    profile_photo TEXT,

    FOREIGN KEY (user_id) REFERENCES users(id)

);
CREATE TABLE client_profiles (

    id SERIAL PRIMARY KEY,

    user_id INT UNIQUE,

    company_name VARCHAR(200),

    company_description TEXT,

    profession VARCHAR(150),

    phone VARCHAR(20),

    address TEXT,

    linkedin_url TEXT,

    github_url TEXT,

    website VARCHAR(200),

    other_profile_url TEXT,

    total_projects_posted INT DEFAULT 0,

    total_spent DOUBLE PRECISION DEFAULT 0,

    profile_photo TEXT,

    FOREIGN KEY (user_id) REFERENCES users(id)

);