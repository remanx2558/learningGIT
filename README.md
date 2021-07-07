# learningGIT

1. Remote to local

a.git clone URL_copied
b.ls (for knowing remote coped or not as it will show directory name then)
c.cd remote_repo_name
d.ls(files present in local computer of remote repository)
e.after getting into remote_repository in local system: git status (tell current branch name /tell weather uptodate with master branch on remote repository/tell its clean or not)

3. adding untracked file to stagging area
a.go in remote_repo present in local directory
b.git status(tell files name not be tracked)
c. git add file_names(add files to stagging area from working directory)(Changes to be committed: is what it call itself)
d.git status(tell files needed to be committed from staging area)
e.git commit -m "commit message"(from the stagging area to local reposiitory)
d.git status(bring back to clean state/ tells local "on  master" is ahead from "origin/master" by 1 commit which is remote  )

4.adding changes from local repo to remote repo(github)
a.git push origin master (origin is branch of github or github copy of our repository and mater is current branch on local system)
b.check changes by refreshing github page


5.making branches
a.git branch (tell all branches)
b.git branch branch_name(make a new branch)
c.git checkout branch_name(activate the named branched)
d.do changes in file and save it and they will be for selected branch only
e. git add file_name_in_which_changes(stagging for name_branch)
f.git commit -m "comment for commit"(committed to name_branch)
g.git checkout master(this will seem revert changes in file as those were done on another branch)
h.git log(specific to activated branch)

6.Merging brnaches
a.git checkout master(go to branch in which want to merge)
b.git merge  named_branch

7.have only master branch on github so need to add named_branch there
a.git pull
b.git checkout named_branch
c.git push(pushes the current branch on github)(suggestion comes)
d.git push --set-upstream origin named_branch


8.Delete branch
a.move to some other branch
b.git branch -d named_branch

9.Modify file
a.do changes in file
b.git status(tells us which files modified)
c.git difftool HEAD(check changes )
d.git add file_name



