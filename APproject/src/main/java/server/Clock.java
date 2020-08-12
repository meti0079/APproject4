package server;

public class Clock  extends Thread{
	private User user1;
	private User user2;
	private long user1LastConnection;
	private long user2LastConnection;
	int timeLeft=58;
	private int maximumTime=60;
	private int time;
	private  final long Frametime=1000/50;
	private float movepessecond;
	private long lastupdate;
	private int CycleUsed;
	private float Cyclehave;
	Game game;
	public Clock(Game game) throws InterruptedException {
		user1LastConnection=System.nanoTime();
		user2LastConnection=System.nanoTime();
		user1=game.getUser1();
		user2=game.getUser2();
		movepessecond=1.0f;
		reset();
		this.game=game;
	}

	public void reset() {
		this.CycleUsed=0;
		this.Cyclehave=0.0f;
		this.lastupdate=getCurrenttime();
	}
	private long getCurrenttime() {
		return System.nanoTime()/1000000;
	}
	public void update() {
		long cur=getCurrenttime();
		float a=(float)(cur-lastupdate)+Cyclehave;
		this.CycleUsed += (int)Math.floor(a / movepessecond);
		this.Cyclehave = a % movepessecond;
		this.lastupdate=cur;
	}
	public boolean hascycle() {
		if(CycleUsed>0) {
			this.CycleUsed--;
			return true;
		}
		return false;
	}
	@Override
	public  void run() {
		while(true) {
			try {	
				long s=System.nanoTime();
				if((s-user1LastConnection)/1000000000>timeLeft) {
					game.exit(0);
				}
				if((s-user2LastConnection)/1000000000>timeLeft) {
					game.exit(1);
				}
				String message="SETTIME>>"+time+"#";
				if(user1.getGameState().equals("training")) {
					ServerMain.WriteMessage(message, user1.getAddress());
				}else {
					ServerMain.WriteMessage(message, user1.getAddress());
					ServerMain.WriteMessage(message, user2.getAddress());					
				}
				update();
				if(hascycle()) {
					time++;
				}
				long e = ( System.nanoTime()-s)/1000000;
				if(e<Frametime) {
					this.sleep(1000);
				}
				if (time==maximumTime) {
					time=0;
					try {
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} catch (Exception e1) {
				System.out.println(255);			
				e1.printStackTrace();
			}
		}
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public long getUser1LastConnection() {
		return user1LastConnection;
	}

	public void setUser1LastConnection(long user1LastConnection) {
		this.user1LastConnection = user1LastConnection;
	}

	public long getUser2LastConnection() {
		return user2LastConnection;
	}

	public void setUser2LastConnection(long user2LastConnection) {
		this.user2LastConnection = user2LastConnection;
	}
	
}
