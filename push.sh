git add .
read -rp "[+] Insert commit message: " msg;
git commit -m "$msg";
git push -u origin master;