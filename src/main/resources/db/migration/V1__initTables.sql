    CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        name VARCHAR(100),
        email VARCHAR(100) UNIQUE,
        password VARCHAR(255),
        role VARCHAR(20),
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    CREATE TABLE skills (
        id SERIAL PRIMARY KEY,
        user_id INT,
        skill_name VARCHAR(100),
        experience INT,
        FOREIGN KEY (user_id) REFERENCES users(id)
    );
    CREATE TABLE project_skills (
        id SERIAL PRIMARY KEY,
        project_id INT,
        skill_name VARCHAR(100),

        FOREIGN KEY (project_id) REFERENCES projects(id)
    );

    CREATE TABLE projects (
        id SERIAL PRIMARY KEY,
        client_id INT,
        title VARCHAR(200),
        description TEXT,
        budget DOUBLE PRECISION,
        deadline DATE,

        status VARCHAR(20) DEFAULT 'OPEN',

        selected_freelancer INT,

        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

        FOREIGN KEY (client_id) REFERENCES users(id),
        FOREIGN KEY (selected_freelancer) REFERENCES users(id)
    );

    CREATE TABLE bids (
         id SERIAL PRIMARY KEY,
         project_id INT,
         freelancer_id INT,
         bid_amount DOUBLE PRECISION,
         proposal TEXT,

         status VARCHAR(20) DEFAULT 'PENDING',

         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

         FOREIGN KEY (project_id) REFERENCES projects(id),
         FOREIGN KEY (freelancer_id) REFERENCES users(id)
     );

    CREATE TABLE submissions (
        id SERIAL PRIMARY KEY,
        project_id INT,
        freelancer_id INT,
        file_url TEXT,
        reference_link TEXT,

        status VARCHAR(20) DEFAULT 'SUBMITTED',

        client_feedback TEXT,

        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

        FOREIGN KEY (project_id) REFERENCES projects(id),
        FOREIGN KEY (freelancer_id) REFERENCES users(id)
    );

    CREATE TABLE reviews (
        id SERIAL PRIMARY KEY,
        project_id INT,
        reviewer_id INT,
        reviewee_id INT,
        rating INT CHECK (rating BETWEEN 1 AND 5),
        comment TEXT,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

        FOREIGN KEY (project_id) REFERENCES projects(id),
        FOREIGN KEY (reviewer_id) REFERENCES users(id),
        FOREIGN KEY (reviewee_id) REFERENCES users(id)
    );
    CREATE TABLE freelancer_profiles (
        id SERIAL PRIMARY KEY,
        user_id INT UNIQUE,
        bio TEXT,
        resume_url TEXT,
        experience INT,
        portfolio_url TEXT,
        profile_photo TEXT,
        FOREIGN KEY (user_id) REFERENCES users(id)
    );
    CREATE TABLE client_profiles (
        id SERIAL PRIMARY KEY,
        user_id INT UNIQUE,
        company_name VARCHAR(200),
        company_description TEXT,
        website VARCHAR(200),
        profile_photo TEXT,
        FOREIGN KEY (user_id) REFERENCES users(id)
    );
    CREATE TABLE bugs (
        id SERIAL PRIMARY KEY,

        project_id INT,
        freelancer_id INT,

        title VARCHAR(200),
        description TEXT,

        status VARCHAR(20) DEFAULT 'OPEN',

        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

        FOREIGN KEY (project_id) REFERENCES projects(id),
        FOREIGN KEY (freelancer_id) REFERENCES users(id)
    );