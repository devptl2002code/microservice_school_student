import { useEffect, useState } from "react";
import api from "../api/api";

export default function StudentList() {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    api.get("/student")
      .then(res => setStudents(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      <h2>Students</h2>
      <ul>
        {students.map(student => (
          <li key={student.id}>{student.name} - {student.age}</li>
        ))}
      </ul>
    </div>
  );
}
