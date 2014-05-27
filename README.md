# Usergrid Perl Client

Usergrid::Client provides a modular object oriented interface to
Apache Usergrid's REST API.

## Installation

### Prerequisites
Usergrid::Client depends on the following modules which can be installed
from CPAN as shown below:

    $ sudo cpan install Moose
    $ sudo cpan install JSON
    $ sudo cpan install REST::Client
    $ sudo cpan install URI::Template
    $ sudo cpan install Log::Log4perl

### Build and install

    $ perl Build.PL
    $ ./Build
    $ ./Build test
    $ sudo ./Build install

### For legacy users on older versions of Perl

    $ perl Makefile.PL
    $ make
    $ make test
    $ sudo make install

## Usage

### Getting started

### Quick start

```perl
#!/usr/bin/perl
use Usergrid::Client;

my $client = Usergrid::Client->new(
  organization => 'test-organization',
  application  => 'test-app',
  api_url      => 'http://localhost:8080'
);

$client->login('johndoe', 'Johndoe123$');

$client->add_entity("books", { name => "Ulysses", author => "James Joyce" });

my $books = $client->get_collection("books");

while ($books->has_next_entity()) {
  my $book = $books->get_next_entity();

  print "Name: "   . $book->get('name')   . "\n";
  print "Author: " . $book->get('author') . "\n";

  $book->set("publisher", "Acme Publisher");
  $client->update_entity($book);
}
```

### Developers

Test coverage reporting requires Devel::Cover module which can be
installed from CPAN as shown:

    $ sudo cpan install Devel::Cover

For generating reports on test coverage:

    $ ./Build testcover

The generated report artifacts are located in cover_db/.

## Release notes

### 0.1
* Initial release

## License
Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
the ASF licenses this file to You under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
