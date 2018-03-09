<?php
if(isset($_GET['id'] && $_GET['etat']){
	$servername = "localhost";
$username = "root";
$password = "";
$dbname = "gateway";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "INSERT INTO sms (id, etat)
VALUES (\'".$_GET['id']."\',\'".$_GET['etat']."\')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
}
	
?>