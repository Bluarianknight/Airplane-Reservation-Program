package newGUI;

public class purchaseDialogController {
	purchaseDialogModel model = new purchaseDialogModel();
	
	public void sendReservation(reservation res) {
		model.setReservationData(res);
	}
}
