<?php 

$db_host  = "localhost"; // PHP variable to store the host address
$db_uid  = "root"; // PHP variable to store the username
$db_pass = ""; // PHP variable to store the password
$db_name  = "victim_human"; // PHP variable to store the Database name  

$db_con = mysqli_connect($db_host,$db_uid,$db_pass,$db_name) or die(mysqli_error($db_con));

function sendNotification($to = '',$data = array()){
    $apiKey = "AIzaSyAjSUou8-D62e0r66Oh4fh--uAHs3prccs";
    $fields = array('to'=>$to,'notification'=>$data);
    $headers = array('Authorization: key ='.$apiKey,'Content-Type:application/json');
    $url = 'https://fcm.googleapis.com/fcm/send';
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
    $result = curl_exec($ch);
    curl_close($ch);
    //echo json_encode($result,true);
    return json_encode($result,true);
}
/*$to = "AAAAtkL3XI0:APA91bExPq5bvisSDU__wWwxWZ7y5Qhcgndgiz7ji3SjGK8Ox0xHI2mPK6Bi1VYBFDOrKdlphuXogDcO7gjUGs4O-bD9cm4ixgAF828q27NFsEf9PfAqx4Aja-p7OMdVKBLSnTuvD2Eu";
$sayari = "Message For Police Station";
$data1 = array('body'=>$sayari);
sendNotification($to,$data1);*/

?>