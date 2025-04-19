import { Routes, Route, Link } from "react-router-dom";
import StudentList from "./components/StudentList";
import SchoolList from "./components/SchoolList";
import AddStudentForm from "./components/AddStudentForm";


function App() {
  return (
    <div>
      <nav>
        <Link to="/students">Students</Link> |{" "}
        <Link to="/schools">Schools</Link>  |{" "}
        <Link to="/add-student">Add New Student</Link>
      </nav>
      <Routes>
        <Route path="/students" element={<StudentList />} />
        <Route path="/schools" element={<SchoolList />} />
        <Route path="/add-student" element={<AddStudentForm />} />
      </Routes>
    </div>
  );
}

export default App;
