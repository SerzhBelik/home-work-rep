package HomeWork1;

public class Course {
    private double[] barriersHeight;
    private int barriersNumber;
    Course(int barriersNumber){
        barriersHeight = new double[barriersNumber];
        this.barriersNumber = barriersNumber;
        for(int i = 0; i<barriersNumber; i++){
            barriersHeight[i] = Math.random()+1;
        }
    }

    public void doIt(Team team){
        for(int i = 0; i<team.teamMembers.length; i++){
            for (int j = 0; j<barriersNumber; j++){
                if(getJumpHeight()>barriersHeight[j]){
                    team.teamMembers[i].personalScore += (int)(barriersHeight[j]*100);
                    if (j==team.teamMembers.length-1){
                        team.teamScore += team.teamMembers[i].personalScore;
                        team.teamMembers[i].setPassedStatus(TeamMember.PASSED);
                    }
                } else {
                    team.teamMembers[i].setPassedStatus(TeamMember.DISCQUAL);
                    team.teamScore += team.teamMembers[i].personalScore;
                    break;
                }
            }
        }
    }

    public double getJumpHeight() {
        return Math.random()+1.2;
    }
}
