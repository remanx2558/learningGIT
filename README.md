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



********************************************************************************************************************************************************
********************************************************************************************************************************************************


touch : create file
ls : listing files in folder : -a:address exact       -l:location in time
rm : files
rmdir: directories
cp:file
cp -r:recursively: directory
mv: just works


git config --list
git config --global user.name "yashwant"

git add period(.) sign


git status:staging
git log:commits


git log --online : check previous cmmits with commit ID
git show comment_id : used with git 
git reflog: show every operation on every branch but git log is specific to a branch and by git reset histry can be lost but not for git reflog

git rebase vs git merge: merge (new commit created at the end ) rebase(just all changes added in last commit ) :adv(looks neat comits):disadv(bramch-branhc-branch : then rebasing becomes complex and headache)


press I-->edit-->Esc--->":wq enter": save and quit  text file


git create branch_name
git checkut branch_name
git checkout -b branch_name: create branch -> switch it: b :before create it

git branch -d branch_name: delete : condition commits should merged
git branch -D branch_name: forced Deletion


//existing project
must have a project locally and remote repository 
git init (in local project)
git add .
git commit -m "random_comment"
git remote add origin <remote_URL>
git push origin master(master here is branch name:can be anything)
(origin variable store remote_url)

git remote (return variables which stores remote_url: origin here)

git remote -v (tell content of origin too)

git remote add origin_new_name <remote_URL>


//fetch changes : remote repo-->local repo --> working directory(stage skip)

git fetch (master (remote)---> origin/master(local)) (remote-->local repo)
git merge origin/master(from which remote and branchyou want to fetch changes to working directory)(local repo-->working direc)


git pull==fetch+merge

git pull origin master

index.html


git clean --f -d : Command for cleaning working repositories(f:force d:delete recursively)

git commit --amend -:Command for changing message of the previous commit , also for content of last commit


git checkout commit_id: move head to this
git switch -->   - To get back to last commit



//
git reset commit_id : go to some previous commit and delete next all commits :code just get unstaged

git reset commit_id --hard  : delete commit+code


//
git revert commit_id: revert+commit
git revert commit_id -n: just revert ---> you commit explicitly


//see rever + abort

