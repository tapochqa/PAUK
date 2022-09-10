cp:
	git add .
	@read -p "Commit message: " m; \
	git commit -m "$$m"
	git push --all gh