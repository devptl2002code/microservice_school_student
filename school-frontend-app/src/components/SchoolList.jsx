import { useEffect, useState } from "react";
import api from "../api/api";

export default function SchoolList() {
  const [schools, setSchools] = useState([]);

  useEffect(() => {
    api.get("/school")
      .then(res => setSchools(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      <h2>Schools</h2>
      <ul>
        {schools.map(school => (
          <li key={school.id}>{school.schoolName} - {school.locationName}</li>
        ))}
      </ul>
    </div>
  );
}
