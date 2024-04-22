package newGUI;

public class purchaseDialogController {
    // Create an instance of the purchaseDialogModel to interact with the model
    purchaseDialogModel model = new purchaseDialogModel();
    
    // Method to send reservation data to the model
    public void sendReservation(reservation res) {
        model.setReservationData(res);
    }
}
