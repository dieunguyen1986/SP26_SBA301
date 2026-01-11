import React from "react";

const HeroSection = () => {
  return (
    <section className="py-5 bg-light">
      <div className="container">
        <div className="row align-items-center">
          
          {/* LEFT CONTENT */}
          <div className="col-lg-6 mb-4 mb-lg-0">
            <span className="badge bg-success mb-3">
              Get 30% off on first enroll
            </span>

            <h1 className="fw-bold display-5 mt-3">
              Advance your <br /> engineering skills <br /> with us.
            </h1>

            <p className="text-muted mt-3">
              Build skills with our courses and mentor from world-class companies.
            </p>

            {/* SEARCH BOX */}
            <div className="d-flex mt-4">
              <input
                type="text"
                className="form-control rounded-pill px-4"
                placeholder="search courses..."
              />
              <button className="btn btn-primary rounded-pill ms-2 px-4">
                🔍
              </button>
            </div>

            {/* FEATURES */}
            <div className="d-flex gap-4 mt-4 text-muted">
              <div>✔ Flexible</div>
              <div>✔ Learning path</div>
              <div>✔ Community</div>
            </div>
          </div>

          {/* RIGHT IMAGE */}
          <div className="col-lg-6 text-center position-relative">
            <div
              className="rounded-4 p-4"
              style={{
                background: "linear-gradient(135deg, #cfe9ff, #e6ddff)",
              }}
            >
              <img
                src="https://themewagon.github.io/E-learning/images/banner/mahila.png"
                alt="student"
                className="img-fluid"
              />
            </div>

            {/* SMALL BADGES */}
            <div
              className="position-absolute top-0 start-0 bg-white shadow-sm rounded px-3 py-2"
              style={{ transform: "translate(-20%, 30%)" }}
            >
              50+ <br /> Available courses
            </div>

            <div
              className="position-absolute top-0 end-0 bg-white shadow-sm rounded px-3 py-2"
              style={{ transform: "translate(20%, 20%)" }}
            >
              No of students 📊
            </div>
          </div>

        </div>
      </div>
    </section>
  );
};

export default HeroSection;