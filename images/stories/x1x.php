<?php
$f = fopen("../m.txt", "w");
fwrite($f, "by misafir");
fclose($f);
unlink("m.txt");
unlink("x.php");
echo "o.k.";
?>