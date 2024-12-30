<!-- Lucas Chew 260971542 -->
<?php

// Define the CSV file name
$csvFile = 'mini5.csv';
$addedValue = false;

// Check if the form has been submitted
if ($_SERVER["REQUEST_METHOD"] === "POST") {

    $row = [
        $_POST['firstName'], 
        $_POST['lastName'], 
        $_POST['email'], 
        $_POST['phone'], 
        $_POST['book'], 
        $_POST['os']
    ];

    if (!in_array("", $row)) {
        $file = fopen($csvFile, "a");
        fputcsv($file, $row);
        fclose($file);
        $addedValue = true;
    }

}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form Data</title>
    <style>

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 8px;
            border: 1px solid;
            border-color: black;
        }

        td {
            text-align: left;
        }

        th {
            background-color: #89b8ee;
            color: white;
            text-align: center;
        }


        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:nth-child(odd) {
            background-color: #dae3f7;
        }

        .button-link {
            display: inline-block;
            margin-top: 25px;
            padding: 10px 20px;
            
            background-color: #89b8ee;
            color: white;
            text-decoration: none;
            border-radius: 10px;

        }

        .button-link:hover {
            background-color: #a1ccf4;
        }

    </style>
</head>
<body>
    <h1>Form Data</h1>

    <?php 
        if (!$addedValue)
        echo "No data to add to the CSV file!"
    ?>

    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Book</th>
            <th>Operating System</th>
        </tr>
        <?php
        $exists = file_exists($csvFile);
        if ($exists)
            $file = fopen($csvFile, "r");
        else
            $file = fopen($csvFile, "a");

        if ($exists) {
            while (($line = fgetcsv($file)) !== false) {

                if (in_array("", $line)) continue;
    
                echo "<tr>";
                foreach ($line as $value) {
                    echo "<td>" . $value . "</td>";
                }
                echo "</tr>";
    
            }
        }

        fclose($file)
        ?>
    </table>

    <a href="mini5.html" class="button-link">Add Another Record</a>
</body>
</html>