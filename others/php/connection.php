<?php

$server = "PMYSQL117.dns-servicio.com"; // server IP
$db = "7057507_administration_db"; // database NAME
$user= "*******"; // User
$password = "*********"; //Password
$pdo = null;

    try{        
        $pdo = new PDO("mysql:host=$server;dbname=$db", $user, $password); 		
        // echo "ok";
    }catch (Exception $E){
         echo "Conexion error:";
    }

?>