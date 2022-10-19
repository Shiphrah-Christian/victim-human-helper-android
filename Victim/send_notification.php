<?php
require('config.php');

	$sqlUser = $db_con->query("SELECT * FROM `police_user`");	
   //$result = $db_con->query($sql);

$rowCount = $sqlUser->num_rows;

if($rowCount>0){
 		while($row = mysqli_fetch_assoc($sqlUser)){
				//$arrayData[] = $row;
    				$sayari = "Message Fro Police Station";
            		//$row['fcmId'];
            		$to = $row['fcmId'];
					$data1 = array('body'=>$sayari);
					sendNotification($to,$data1);
		    
		}
	    $data =array('Status'=>'True','Message'=>'Send Notification');
		print(json_encode($data));
		exit;
}
else{
	    $data =array('Status'=>'False','Message'=>'Login Unsuccessfully');
		print(json_encode($data));
		exit;
}

 mysqli_close($db_con);   
?>

