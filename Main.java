import dao.AssetDAO;
import dao.PatchDAO;
import model.Asset;
import model.Patch;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        AssetDAO assetDAO = new AssetDAO();
        PatchDAO patchDAO = new PatchDAO();

        int choice;

        do {
            System.out.println("\n===== IT ASSET TRACKER =====");
            System.out.println("1. Add Asset");
            System.out.println("2. View Assets");
            System.out.println("3. Update Assets");
            System.out.println("4. Delete Assets");
            System.out.println("5. Search Assets");
            System.out.println("6. Add Patch");
            System.out.println("7. Install Patch");
            System.out.println("8. View Installed Patches");
            System.out.println("9. Assets Needing Updates");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Asset Name: ");
                    String name = input.nextLine();

                    System.out.print("Asset Type: ");
                    String type = input.nextLine();

                    System.out.print("Serial Number: ");
                    String serial = input.nextLine();

                    System.out.print("Assigned To: ");
                    String assigned = input.nextLine();

                    System.out.print("Purchase Date (YYYY-MM-DD): ");
                    String purchaseDate = input.nextLine();

                    System.out.print("Status: ");
                    String status = input.nextLine();

                    Asset asset = new Asset(
                            name,
                            type,
                            serial,
                            assigned,
                            purchaseDate,
                            status
                    );

                    assetDAO.addAsset(asset);
                    break;

                case 2:
                    assetDAO.viewAssets();
                    break;

                case 3:

    System.out.print("Enter Asset ID: ");
    int id = input.nextInt();
    input.nextLine();

    System.out.print("Enter new assigned user: ");
    String newAssigned = input.nextLine();

    System.out.print("Enter new status: ");
    String newStatus = input.nextLine();

    assetDAO.updateAsset(id, newStatus, newAssigned);

    break;

    case 4:

    System.out.print("Enter Asset ID to delete: ");
    int deleteId = input.nextInt();
    input.nextLine();

    assetDAO.deleteAsset(deleteId);

    break;

   case 5:

    System.out.print("Enter Serial Number: ");
    String searchSerial = input.nextLine();

    assetDAO.searchAsset(searchSerial);

    break;

    case 6:

    System.out.print("Patch Name: ");
    String patchName = input.nextLine();

    System.out.print("Version: ");
    String version = input.nextLine();

    System.out.print("Release Date (YYYY-MM-DD): ");
    String releaseDate = input.nextLine();

    Patch patch = new Patch(
            patchName,
            version,
            releaseDate
    );

    patchDAO.addPatch(patch);

    break;

    case 7:

    System.out.print("Enter Asset ID: ");
    int assetId = input.nextInt();

    System.out.print("Enter Patch ID: ");
    int patchId = input.nextInt();
    input.nextLine();

    System.out.print("Installation Status: ");
    String installationStatus = input.nextLine();


    patchDAO.installPatch(
            assetId,
            patchId,
            installationStatus
    );

    break;

    case 8:

    patchDAO.viewInstalledPatches();

    break;

    case 9:

    patchDAO.assetsNeedingUpdates();

    break;

    case 10:
    System.out.println("Goodbye!");
    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 10);


        input.close();
    }
}
