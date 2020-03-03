<?php


include("connection.php"); 

	$id = $_POST['id'];
	$product_id = $_POST['product_id'];
    $name = $_POST['name'];
    $description = $_POST['description'];
	$price = $_POST['price'];
	$stock = $_POST['stock'];
	$image = $_POST['image'];
	

	if($id==null){
		
		if ($image != null){
			
			uploadImage($pdo, $product_id, $name, $description, $price, $stock, $image);
			
		}else{ //upload product without image
					
			insertProduct($pdo, $product_id, $name, $description, $stock, $price);
		}
		
	}else{
		
		if ($image != null){
			
			updateImage($pdo, $id, $product_id, $name, $description, $price, $stock, $image);
			
		}else{ //upload product without image
					
			updateProduct($pdo, $id, $product_id, $name, $description, $price, $stock);
		}
				
	}



function uploadImage($pdo, $product_id, $name, $description, $price, $stock, $image){
	

	$date = date('YmdHis');
	
	//define image´s path
    $imagen = null;
	$path = "images/$date.png"; 
	$url = "https://www.moimah.com/gluonfx/uploads/$path"; 


	//get image, decode and upload to server	  
	 file_put_contents($path,base64_decode($image));  
	
	//call function to upload with the url
	insertProductWithUrl($pdo, $product_id, $name, $description, $price, $stock, $url);
 
}


function insertProduct($pdo, $product_id, $name, $description, $price, $stock){
			
		$sql = "INSERT INTO product(product_id, name, description, price, stock) VALUES (?, ?, ?, ?, ?)";
		$stmt= $pdo->prepare($sql);			
		$stmt->execute([$product_id, $name, $description, $price, $stock]);

}

function insertProductWithUrl($pdo, $product_id, $name, $description, $price, $stock, $url){
	
		$sql = "INSERT INTO product(product_id, name, description, price, stock, url) VALUES (?, ?, ?, ?, ?, ?)";
		$stmt= $pdo->prepare($sql);			
		$stmt->execute([$product_id, $name, $description, $price, $stock, $url]);
}

function updateImage($pdo, $id, $product_id, $name, $description, $price, $stock, $image){
	
	
		$date = date('YmdHis');
	
		//define image´s path
		$imagen = null;
		$path = "images/$date.png"; 
		$url = "https://www.moimah.com/gluonfx/uploads/$path"; 


		//get image, decode and upload to server	  
		 file_put_contents($path,base64_decode($image));    
		
		//call function to upload with the url
		updateProductWithUrl($pdo, $id, $product_id, $name, $description, $price, $stock, $url);
	
 
}

function updateProduct($pdo, $id, $product_id, $name, $description, $price, $stock){
	
		$sql = "UPDATE PRODUCT SET product_id = ?, name = ?, description = ?, price = ?, stock = ? WHERE id = ?";
		$stmt= $pdo->prepare($sql);			
		$stmt->execute([$product_id, $name, $description, $price, $stock, $id]);
}

function updateProductWithUrl($pdo, $id, $product_id, $name, $description, $price, $stock, $url){
	
		$sql = "UPDATE PRODUCT SET product_id = ?, name = ?, description = ?, price = ?, stock = ?, url = ? WHERE id = ?";
		$stmt= $pdo->prepare($sql);			
		$stmt->execute([$product_id, $name, $description, $price, $stock, $url, $id]);
}

?>