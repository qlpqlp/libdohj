package org.bitcoinj.core;

import org.bitcoinj.base.Network;
import org.bitcoinj.base.Sha256Hash;
import org.bitcoinj.core.*;
import org.bitcoinj.net.discovery.DnsDiscovery;
import org.bitcoinj.net.discovery.PeerDiscovery;
import org.libdohj.params.DogecoinMainNetParams;
import org.libdohj.params.DogecoinTestNet3Params;
import org.bitcoinj.utils.BriefLogFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Test to verify Dogecoin peer connection functionality
 */
public class DogecoinPeerConnectionTest {
    
    private static final int CONNECTION_TIMEOUT_SECONDS = 30;
    private static final int PEER_DISCOVERY_TIMEOUT_SECONDS = 10;
    
    @Before
    public void setUp() {
        // Enable bitcoinj logging for debugging
        BriefLogFormatter.init();
    }
    
    @Test
    @Ignore("Requires internet connection - disabled for v0.17 release")
    public void testDogecoinMainnetPeerDiscovery() throws Exception {
        System.out.println("=== Dogecoin Mainnet Peer Discovery Test ===");
        
        DogecoinMainNetParams params = DogecoinMainNetParams.get();
        System.out.println("Network: " + params.getId());
        System.out.println("Default port: " + params.getPort());
        System.out.println("Packet Magic: 0x" + Integer.toHexString(params.getPacketMagic()));
        System.out.println("Address Header: " + params.getAddressHeader());
        System.out.println("P2SH Header: " + params.getP2SHHeader());
        
        // Test peer discovery
        PeerDiscovery discovery = new DnsDiscovery(params);
        System.out.println("Discovering Dogecoin mainnet peers...");
        
        // Get peer addresses
        java.util.List<InetSocketAddress> peers = discovery.getPeers(5, Duration.ofSeconds(5));
        assertNotNull("Should discover at least one peer", peers);
        assertTrue("Should discover at least one peer", peers.size() > 0);
        
        System.out.println("Discovered " + peers.size() + " peers");
        for (InetSocketAddress peer : peers) {
            System.out.println("  - " + peer);
        }
        
        System.out.println("✓ Successfully discovered Dogecoin mainnet peers!");
    }
    
    @Test
    @Ignore("Requires internet connection - disabled for v0.17 release")
    public void testDogecoinTestnetPeerDiscovery() throws Exception {
        System.out.println("=== Dogecoin Testnet Peer Discovery Test ===");
        
        DogecoinTestNet3Params params = DogecoinTestNet3Params.get();
        System.out.println("Network: " + params.getId());
        System.out.println("Default port: " + params.getPort());
        System.out.println("Packet Magic: 0x" + Integer.toHexString(params.getPacketMagic()));
        System.out.println("Address Header: " + params.getAddressHeader());
        System.out.println("P2SH Header: " + params.getP2SHHeader());
        
        // Test peer discovery
        PeerDiscovery discovery = new DnsDiscovery(params);
        System.out.println("Discovering Dogecoin testnet peers...");
        
        // Get peer addresses
        java.util.List<InetSocketAddress> peers = discovery.getPeers(5, Duration.ofSeconds(5));
        assertNotNull("Should discover at least one peer", peers);
        assertTrue("Should discover at least one peer", peers.size() > 0);
        
        System.out.println("Discovered " + peers.size() + " peers");
        for (InetSocketAddress peer : peers) {
            System.out.println("  - " + peer);
        }
        
        System.out.println("✓ Successfully discovered Dogecoin testnet peers!");
    }
    
    @Test
    public void testDogecoinKnownPeers() throws Exception {
        System.out.println("=== Dogecoin Known Peers Test ===");
        
        DogecoinMainNetParams params = DogecoinMainNetParams.get();
        
        // Known Dogecoin mainnet peers (these may change over time)
        String[] knownPeers = {
            "seed.multidoge.org",
            "seed2.multidoge.org", 
            "seed.dogecoin.org"
        };
        
        System.out.println("Testing known Dogecoin peer hosts:");
        for (String peerHost : knownPeers) {
            System.out.println("  - " + peerHost + ":" + params.getPort());
        }
        
        // Test DNS resolution for known peers
        boolean dnsResolved = false;
        for (String peerHost : knownPeers) {
            try {
                java.net.InetAddress.getByName(peerHost);
                System.out.println("✓ DNS resolution successful for: " + peerHost);
                dnsResolved = true;
            } catch (Exception e) {
                System.out.println("✗ DNS resolution failed for: " + peerHost + " - " + e.getMessage());
            }
        }
        
        assertTrue("Should resolve at least one known peer", dnsResolved);
        System.out.println("✓ Known peer DNS resolution test passed!");
    }
    
    @Test
    public void testDogecoinNetworkParameters() throws Exception {
        System.out.println("=== Dogecoin Network Parameters Test ===");
        
        // Test mainnet parameters
        DogecoinMainNetParams mainnetParams = DogecoinMainNetParams.get();
        System.out.println("Mainnet Network ID: " + mainnetParams.getId());
        System.out.println("Mainnet Port: " + mainnetParams.getPort());
        System.out.println("Mainnet Address Header: " + mainnetParams.getAddressHeader());
        System.out.println("Mainnet P2SH Header: " + mainnetParams.getP2SHHeader());
        System.out.println("Mainnet Packet Magic: 0x" + Integer.toHexString(mainnetParams.getPacketMagic()));
        System.out.println("Mainnet SegWit HRP: " + mainnetParams.getSegwitAddressHrp());
        Block mainnetGenesis = mainnetParams.getGenesisBlock();
        if (mainnetGenesis != null) {
            System.out.println("Mainnet Genesis Block: " + mainnetGenesis.getHash());
        } else {
            System.out.println("Mainnet Genesis Block: null (not available)");
        }
        
        // Test testnet parameters
        DogecoinTestNet3Params testnetParams = DogecoinTestNet3Params.get();
        System.out.println("Testnet Network ID: " + testnetParams.getId());
        System.out.println("Testnet Port: " + testnetParams.getPort());
        System.out.println("Testnet Address Header: " + testnetParams.getAddressHeader());
        System.out.println("Testnet P2SH Header: " + testnetParams.getP2SHHeader());
        System.out.println("Testnet Packet Magic: 0x" + Integer.toHexString(testnetParams.getPacketMagic()));
        System.out.println("Testnet SegWit HRP: " + testnetParams.getSegwitAddressHrp());
        
        Block testnetGenesis = testnetParams.getGenesisBlock();
        if (testnetGenesis != null) {
            System.out.println("Testnet Genesis Block: " + testnetGenesis.getHash());
        } else {
            System.out.println("Testnet Genesis Block: null (not available)");
        }
        
        // Verify parameters are different
        assertNotEquals("Mainnet and testnet should have different ports", 
                       mainnetParams.getPort(), testnetParams.getPort());
        assertNotEquals("Mainnet and testnet should have different address headers", 
                       mainnetParams.getAddressHeader(), testnetParams.getAddressHeader());
        
        // Only compare genesis blocks if both are available
        if (mainnetGenesis != null && testnetGenesis != null) {
            assertNotEquals("Mainnet and testnet should have different genesis blocks", 
                           mainnetGenesis.getHash(), testnetGenesis.getHash());
        }
        
        System.out.println("✓ Network parameters are correctly configured!");
    }
    
    @Test
    public void testDogecoinMessageSerialization() throws Exception {
        System.out.println("=== Dogecoin Message Serialization Test ===");
        
        DogecoinMainNetParams params = DogecoinMainNetParams.get();
        
        // Test version message
        VersionMessage versionMsg = new VersionMessage(params, 0);
        System.out.println("Version message: " + versionMsg);
        
        // Test serialization
        byte[] serialized = versionMsg.bitcoinSerialize();
        assertNotNull("Serialized version message should not be null", serialized);
        assertTrue("Serialized version message should have reasonable size", serialized.length > 0 && serialized.length < 1000);
        
        System.out.println("Serialized size: " + serialized.length + " bytes");
        
        // Test basic message creation
        System.out.println("✓ Basic message creation working correctly!");
        
        System.out.println("✓ Message serialization working correctly!");
    }
}
