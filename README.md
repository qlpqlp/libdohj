[![Build Status](https://travis-ci.org/dogecoin/libdohj.svg?branch=master)](https://travis-ci.org/dogecoin/libdohj)

# 🐕 libdohj - Dogecoin Java Library

**libdohj** is a comprehensive Java library for Dogecoin development, built on top of bitcoinj. It provides full Dogecoin network support including address generation, peer connectivity, AuxPoW (merged mining), and blockchain operations.

## ✨ Features

### 🎯 Core Dogecoin Functionality
- **Dogecoin Address Generation** - Generate P2PKH addresses with proper "D" prefix
- **Network Connectivity** - Connect to Dogecoin mainnet and testnet peers
- **AuxPoW Support** - Auxiliary Proof of Work for merged mining with Litecoin
- **Merkle Branch Validation** - Complete Merkle tree validation for AuxPoW
- **Block & Transaction Handling** - Full blockchain operations
- **SegWit Support** - Native SegWit address generation with "doge" prefix

### 🔧 Dogecoin-Specific Extensions
- **AltcoinSerializer** - Custom serialization for Dogecoin protocol
- **AltcoinBlock** - Enhanced block class with Dogecoin features
- **Network Parameters** - Proper Dogecoin mainnet/testnet configuration
- **Address Headers** - Correct address prefixes (30 for mainnet, 113 for testnet)
- **Packet Magic** - Dogecoin-specific magic bytes (0xc0c0c0c0)

## 🚀 Quick Start

### Prerequisites
- **Java 8+** (Java 7 for core modules)
- **Gradle 3.4+** - for building the project
- **Maven/Gradle** - for dependency management

### Installation

#### Option 1: Gradle
```gradle
dependencies {
    implementation 'org.dogecoin:libdohj:1.0.0'
}
```

#### Option 2: Maven
```xml
<dependency>
    <groupId>org.dogecoin</groupId>
    <artifactId>libdohj</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Option 3: Build from Source
```bash
git clone https://github.com/dogecoin/libdohj.git
cd libdohj
./gradlew build
```

## 📖 Usage Examples

### 1. Generate a Dogecoin Address

```java
import org.libdohj.params.DogecoinMainNetParams;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.base.LegacyAddress;

// Create a new key pair
ECKey key = new ECKey();
DogecoinMainNetParams params = DogecoinMainNetParams.get();

// Generate Dogecoin address (starts with "D")
LegacyAddress address = LegacyAddress.fromPubKeyHash(params, key.getPubKeyHash());

System.out.println("Private Key (WIF): " + key.getPrivateKeyEncoded(params));
System.out.println("Public Key: " + key.getPublicKeyAsHex());
System.out.println("Dogecoin Address: " + address.toString());
// Output: DMWrCGEYtFqX39ttqHLwDHzDjtUyyqUe3L
```

### 2. Connect to Dogecoin Peers

```java
import org.libdohj.params.DogecoinMainNetParams;
import org.bitcoinj.net.discovery.DnsDiscovery;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.core.BlockChain;
import org.bitcoinj.store.MemoryBlockStore;

// Set up Dogecoin mainnet
DogecoinMainNetParams params = DogecoinMainNetParams.get();
PeerGroup peerGroup = new PeerGroup(params, new BlockChain(params, new MemoryBlockStore()));

// Discover peers
DnsDiscovery discovery = new DnsDiscovery(params);
List<InetSocketAddress> peers = discovery.getPeers(10, Duration.ofSeconds(10));

System.out.println("Discovered " + peers.size() + " Dogecoin peers:");
peers.forEach(peer -> System.out.println("  - " + peer));

// Connect to peers
peerGroup.start();
peers.forEach(peer -> peerGroup.addAddress(new PeerAddress(peer)));
```

### 3. Testnet Address Generation

```java
import org.libdohj.params.DogecoinTestNet3Params;

// Generate testnet address (starts with "n" or "2")
DogecoinTestNet3Params testnetParams = DogecoinTestNet3Params.get();
ECKey key = new ECKey();
LegacyAddress testnetAddress = LegacyAddress.fromPubKeyHash(testnetParams, key.getPubKeyHash());

System.out.println("Testnet Address: " + testnetAddress.toString());
// Output: nZLYTKhCpjyuGEi4c9JVU9RVPxZeN4xPgy
```

### 4. AuxPoW (Merged Mining) Support

```java
import org.bitcoinj.core.AuxPoW;
import org.bitcoinj.core.AltcoinBlock;

// Create AuxPoW for merged mining
AuxPoW auxPoW = new AuxPoW(/* parameters */);

// Validate AuxPoW structure
boolean isValid = auxPoW.checkProofOfWork(/* hash, target */);

// Use with AltcoinBlock
AltcoinBlock block = new AltcoinBlock(params, /* block data */);
```

### 5. Network Parameters

```java
import org.libdohj.params.DogecoinMainNetParams;
import org.libdohj.params.DogecoinTestNet3Params;

// Mainnet parameters
DogecoinMainNetParams mainnet = DogecoinMainNetParams.get();
System.out.println("Mainnet Port: " + mainnet.getPort());           // 22556
System.out.println("Address Header: " + mainnet.getAddressHeader()); // 30
System.out.println("P2SH Header: " + mainnet.getP2SHHeader());       // 22
System.out.println("Packet Magic: 0x" + Integer.toHexString(mainnet.getPacketMagic())); // 0xc0c0c0c0
System.out.println("SegWit HRP: " + mainnet.getSegwitAddressHrp());  // doge

// Testnet parameters
DogecoinTestNet3Params testnet = DogecoinTestNet3Params.get();
System.out.println("Testnet Port: " + testnet.getPort());           // 44556
System.out.println("Address Header: " + testnet.getAddressHeader()); // 113
System.out.println("P2SH Header: " + testnet.getP2SHHeader());       // 196
System.out.println("Packet Magic: 0x" + Integer.toHexString(testnet.getPacketMagic())); // 0xfcc1b7dc
System.out.println("SegWit HRP: " + testnet.getSegwitAddressHrp());  // tdge
```

## 🏗️ Building from Source

### Command Line
```bash
# Clone the repository
git clone https://github.com/dogecoin/libdohj.git
cd libdohj

# Build the project
./gradlew clean build

# Generate documentation
./gradlew javadoc

# Run tests
./gradlew test
```

### IDE Setup
1. **IntelliJ IDEA**: `File | New | Project from Existing Sources` → Select `build.gradle`
2. **Eclipse**: Import as Gradle project
3. **VS Code**: Open folder and install Java extension pack

## 🧪 Testing

Run the comprehensive test suite:

```bash
# Run all tests
./gradlew test

# Run specific tests
./gradlew test --tests "*DogecoinAddressTest*"
./gradlew test --tests "*DogecoinPeerConnectionTest*"
./gradlew test --tests "*AuxPoWTest*"
```

## 📚 API Documentation

- **Network Parameters**: `org.libdohj.params.DogecoinMainNetParams`, `org.libdohj.params.DogecoinTestNet3Params`
- **Address Generation**: `org.bitcoinj.base.LegacyAddress`
- **Peer Discovery**: `org.bitcoinj.net.discovery.DnsDiscovery`
- **AuxPoW**: `org.bitcoinj.core.AuxPoW`
- **Altcoin Blocks**: `org.bitcoinj.core.AltcoinBlock`

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guidelines](CONTRIBUTING.md) for details.

### Development Setup
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 👥 Maintainers

- **Paulo Vidal** - [GitHub](https://github.com/qlpqlp) | [X/Twitter](https://x.com/inevitable360)
- **Dogecoin Foundation** - [Website](https://foundation.dogecoin.com/)

## 🙏 Acknowledgments

- Built on top of [bitcoinj](https://github.com/bitcoinj/bitcoinj) by Mike Hearn
- Inspired by the Dogecoin community
- Special thanks to all contributors

## 📞 Support

- **Issues**: [GitHub Issues](https://github.com/dogecoin/libdohj/issues)
- **Discussions**: [GitHub Discussions](https://github.com/dogecoin/libdohj/discussions)
- **Dogecoin Community**: [r/dogecoin](https://reddit.com/r/dogecoin)

---

**Much wow! Such library! Very Dogecoin! 🐕💫**

