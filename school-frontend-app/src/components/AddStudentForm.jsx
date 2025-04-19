import { useEffect, useState } from "react";
import api from "../api/api";
import { useNavigate } from "react-router-dom";

export default function AddStudentForm() {
  const [formData, setFormData] = useState({
    name: "",
    age: "",
    gender: "",
    schoolId: "",
  });

  const [schools, setSchools] = useState([]);
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch schools on component load
    const fetchSchools = async () => {
      try {
        const response = await api.get("/school");
        setSchools(response.data);
      } catch (error) {
        console.error("Error fetching schools:", error);
      }
    };
    fetchSchools();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const studentPayload = {
        ...formData,
        age: parseInt(formData.age),
        schoolId: parseInt(formData.schoolId),
      };

      const response = await api.post("/student", studentPayload);
      setMessage("Student added successfully!");
      setFormData({
        name: "",
        age: "",
        gender: "",
        schoolId: "",
      });

      // Redirect to student list after success
      setTimeout(() => navigate("/students"), 1000);
    } catch (error) {
      console.error("Error adding student:", error);
      setMessage("Error adding student.");
    }
  };

  return (
    <div>
      <h2>Add Student</h2>
      {message && <p>{message}</p>}
      <form onSubmit={handleSubmit} style={{ maxWidth: "400px" }}>
        <div>
          <label>Name:</label><br />
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Age:</label><br />
          <input
            type="number"
            name="age"
            value={formData.age}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Gender:</label><br />
          <select
            name="gender"
            value={formData.gender}
            onChange={handleChange}
            required
          >
            <option value="">Select gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
          </select>
        </div>
        <div>
          <label>School:</label><br />
          <select
            name="schoolId"
            value={formData.schoolId}
            onChange={handleChange}
            required
          >
            <option value="">Select school</option>
            {schools.map((school) => (
              <option key={school.id} value={school.id}>
                {school.schoolName} ({school.locationName})
              </option>
            ))}
          </select>
        </div>
        <br />
        <button type="submit">Add Student</button>
      </form>
    </div>
  );
}
