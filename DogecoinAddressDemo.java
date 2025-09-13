import org.bitcoinj.crypto.ECKey;
import org.bitcoinj.base.LegacyAddress;
import org.bitcoinj.base.ScriptType;
import org.libdohj.params.DogecoinMainNetParams;
import org.libdohj.params.DogecoinTestNet3Params;

public class DogecoinAddressDemo {
    public static void main(String[] args) {
        System.out.println("=== Dogecoin Address Generation Demo ===\n");
        
        // Test with mainnet
        generateAddress(DogecoinMainNetParams.get(), "Mainnet");
        
        // Test with testnet
        generateAddress(DogecoinTestNet3Params.get(), "Testnet");
    }
    
    private static void generateAddress(org.bitcoinj.core.NetworkParameters params, String networkName) {
        System.out.println("--- " + networkName + " ---");
        
        try {
            // Generate a new ECKey
            ECKey key = new ECKey();
            
            // Show network parameters
            System.out.println("Network ID: " + params.getId());
            System.out.println("Address Header: " + params.getAddressHeader());
            System.out.println("P2SH Header: " + params.getP2SHHeader());
            
            // Create P2PKH address
            LegacyAddress address = LegacyAddress.fromKey(params, key);
            
            // Display results
            System.out.println("Private Key (WIF): " + key.getPrivateKeyEncoded(params).toString());
            System.out.println("Public Key: " + key.getPublicKeyAsHex());
            System.out.println("P2PKH Address: " + address.toString());
            System.out.println("Address Type: " + address.getOutputScriptType());
            
            // Check address format
            if (networkName.equals("Mainnet")) {
                if (address.toString().startsWith("D")) {
                    System.out.println("✓ Mainnet address format is correct (starts with 'D')");
                } else {
                    System.out.println("⚠ Mainnet address format is unexpected (starts with '" + 
                        address.toString().substring(0, 1) + "' instead of 'D')");
                }
            } else {
                if (address.toString().startsWith("n") || address.toString().startsWith("2")) {
                    System.out.println("✓ Testnet address format is correct");
                } else {
                    System.out.println("⚠ Testnet address format is unexpected (starts with '" + 
                        address.toString().substring(0, 1) + "' instead of 'n' or '2')");
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error generating " + networkName + " address: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println();
    }
}
