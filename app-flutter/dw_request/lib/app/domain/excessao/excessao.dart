class Excessao implements Exception{
  String causa;

  Excessao(this.causa);

  @override
  String toString(){
    return causa;
  }
}