git add .
read -p "[+] Insert commit message: " msg;
git commit -m "$msg";
git push -u origin master;