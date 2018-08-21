package HomeWork1;

public class Team {
    private String teamName;
    TeamMember[] teamMembers;
    int teamScore = 0;
    Team(String teamName, TeamMember ... teamMemberName){
        this.teamName = teamName;
        teamMembers = new TeamMember[teamMemberName.length];

        for(int i = 0; i < teamMemberName.length; i++) {
            teamMembers[i] = teamMemberName[i];
        }

    }

    public String getTeamMembersInfo(){
        StringBuilder s = new StringBuilder("Team " + teamName + " consist: ");

        for (int i =0; i < this.teamMembers.length; i++){

            if(i!=0) s.append(", ");
            s.append(teamMembers[i].getName());
        }
        s.append(".");
        return s.toString();
    }

    public String getWhoPassed(){
        StringBuilder s = new StringBuilder("Course passed: ");
        int j = 0;
        for (int i =0; i < this.teamMembers.length; i++){
            if(teamMembers[i].getPassedStatus().equals(TeamMember.PASSED)) {
                if(j!=0) s.append(", ");
                s.append(teamMembers[i].getName());
                j++;
            } else continue;
        }
        s.append(".");
        return s.toString();
    }

    public void showResults(){
        System.out.println("Общий результат команды " + this.teamName + ": " + this.teamScore);
        System.out.println("Личные результаты членов команды:");
        for(int i = 0; i<this.teamMembers.length; i++){
            System.out.println(teamMembers[i].getName() + ":" + teamMembers[i].personalScore + " - " + teamMembers[i].getPassedStatus());
        }
    }
    }
